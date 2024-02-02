package com.cooksys.socialmedia.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cooksys.socialmedia.dtos.*;
import com.cooksys.socialmedia.entities.Credentials;
import com.cooksys.socialmedia.entities.Hashtag;
import com.cooksys.socialmedia.entities.Tweet;
import com.cooksys.socialmedia.entities.User;
import com.cooksys.socialmedia.exceptions.BadRequestException;
import com.cooksys.socialmedia.exceptions.NotAuthorizedException;
import com.cooksys.socialmedia.exceptions.NotFoundException;
import com.cooksys.socialmedia.mappers.CredentialsMapper;
import com.cooksys.socialmedia.mappers.TweetMapper;
import com.cooksys.socialmedia.mappers.UserMapper;
import com.cooksys.socialmedia.repositories.HashtagRepository;
import com.cooksys.socialmedia.repositories.TweetRepository;
import com.cooksys.socialmedia.repositories.UserRepository;
import com.cooksys.socialmedia.services.TweetService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TweetServiceImpl implements TweetService {

    private final TweetRepository tweetRepository;
    private final TweetMapper tweetMapper;
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final CredentialsMapper credentialsMapper;
    private final HashtagRepository hashtagRepository;

    @Override
    public List<TweetResponseDto> getAllTweets() {
        // Get non-deleted tweets in reverse chronological order
        List<Tweet> nonDeletedTweets = tweetRepository.findByDeletedFalseOrderByPostedDesc();
        return tweetMapper.entitiesToDtos(nonDeletedTweets);
    }

    // TODO: reimplement this once GET tweets/{id} is created
    @Override
    public TweetResponseDto deleteTweetById(Long tweetId, CredentialsDto credentialsDto) {
        Tweet tweet = tweetRepository.findById(tweetId).orElseThrow(() -> new IllegalArgumentException("Invalid tweet ID: " + tweetId));

        if (tweet.isDeleted()) {
            throw new NotFoundException("Tweet with ID: " + tweetId + " not found");
        }
        // Check if the user has permission to delete the tweet
        if (!tweet.getAuthor().getCredentials().getUsername().equals(credentialsDto.getUsername()) || !tweet.getAuthor().getCredentials().getPassword().equals(credentialsDto.getPassword())) {
            throw new NotAuthorizedException("Unauthorized to delete tweet with ID: " + tweetId);
        }

        tweet.setDeleted(true);
        tweetRepository.save(tweet);

        return tweetMapper.entityToDto(tweet);
    }
    
    // TODO: reimplement this once GET tweets/{id} is created
    @Override
    public List<UserResponseDto> getUsersMentionedByTweetId(Long tweetId) {
        Tweet tweet = tweetRepository.findById(tweetId).orElseThrow(() -> new IllegalArgumentException("Invalid tweet ID: " + tweetId));

        if (tweet.isDeleted()) {
            throw new NotFoundException("Tweet with ID: " + tweetId + " not found");
        }

        List<User> allMentionedUsers = tweet.getMentionedUsers();

        if (allMentionedUsers.isEmpty()) {
            throw new NotFoundException("No users mentioned in this tweet: " + tweetId);
        }
        ArrayList<User> mentionedUsersNotDeleted = new ArrayList<>();
        for (User user : allMentionedUsers) {
            if (user.isDeleted()) {
                continue;
            }
            mentionedUsersNotDeleted.add(user);
        }

        return userMapper.entitiesToDtos(mentionedUsersNotDeleted);
    }

    @Override
    public TweetResponseDto createRepost(Long tweetId, Credentials credentials) {
        Tweet tweet = tweetRepository.findById(tweetId).orElseThrow(() -> new NotFoundException("Invalid tweet ID: " + tweetId));

        if (tweet.isDeleted()) {
            throw new NotFoundException("Tweet with ID: " + tweetId + " not found");
        }
        Tweet newTweet = new Tweet();
        newTweet.setAuthor(tweet.getAuthor());
        newTweet.setRepostOf(tweet);

        return tweetMapper.entityToDto((tweetRepository.saveAndFlush(newTweet)));
    }

    @Override
    public ContextDto getContext(Long tweetId) {
        Tweet tweet = tweetRepository.findById(tweetId).orElseThrow(() -> new NotFoundException("Invalid tweet ID: " + tweetId));

        if (tweet.isDeleted()) {
            throw new NotFoundException("Tweet with ID: " + tweetId + " not found");
        }

        ContextDto context = new ContextDto();
        List<TweetResponseDto> replyTos = new ArrayList<>();
        List<TweetResponseDto> replies = new ArrayList<>();

        TweetResponseDto tweetResponseDto = tweetMapper.entityToDto(tweet);
        getInReplyToHelper(tweetResponseDto, replyTos);
        getRepliesHelper(tweet.getReplies(), replies);


        context.setTarget(tweetResponseDto);
        context.setBefore(replyTos);
        context.setAfter(replies);

        return context;
    }

    private void getInReplyToHelper(TweetResponseDto tweet, List<TweetResponseDto> replyTos) {
        Tweet t = tweetMapper.dtoToEntity(tweet.getInReplyTo());
        if (t == null) {
            return;
        }
        replyTos.add(tweetMapper.entityToDto(t));
        getInReplyToHelper(tweet.getInReplyTo(), replyTos);
    }

    private void getRepliesHelper(List<Tweet> tweets, List<TweetResponseDto> replies) {
        if (tweets != null) {
            for (Tweet t : tweets) {
                if (t.isDeleted()) {
                    continue;
                }
                replies.add(tweetMapper.entityToDto(t));
                getRepliesHelper(t.getReplies(), replies);
            }
        }
    }
  
  @Override
  public TweetResponseDto getTweetById(Long id) {
	  
	  Optional<Tweet> current = tweetRepository.findById(id);
	  
	  if(current.isEmpty()) {
		  throw new NotFoundException("Tweet not found.");
	  }
	  
	  return tweetMapper.entityToDto(current.get());
  }
  
  @Override
  public List<TweetResponseDto> getRepostsById(Long id){
	  Optional<Tweet> current = tweetRepository.findById(id);
	  
	  if(current.isEmpty()) {
		  throw new NotFoundException("Tweet not found.");
	  }
	  
	  Tweet finall = current.get();
	  
	  if(finall.isDeleted()) {
		  throw new NotFoundException("Tweet not found.");
	  }
	  
	  List<TweetResponseDto> repostList = new ArrayList<>();
	  
	  for(Tweet t: finall.getReposts()) {
		  if(t.isDeleted() == false) {
			  repostList.add(tweetMapper.entityToDto(t));
		  }
	  }
	  
	  return repostList;
  }
  
  @Override
  public List<UserResponseDto> getLikesById(Long id){
	  Optional<Tweet> current = tweetRepository.findById(id);
	  
	  if(current.isEmpty()) {
		  throw new NotFoundException("Tweet not found.");
	  }
	  
	  Tweet finall = current.get();
	  
	  List<UserResponseDto> userList = new ArrayList<>();
	  
	  for(User u: finall.getLikedByUsers()) {
		  if(u.isDeleted()==false) {
			  userList.add(userMapper.entityToDto(u));
		  }
	  }
	  return userList;
  }
  
  @Override
  public TweetResponseDto createReply(Long id, TweetRequestDto tweetRequest) {
  	Tweet current = new Tweet();
  	CredentialsDto credentials = tweetRequest.getCredentials();
  	Optional<Tweet> checker = tweetRepository.findById(id);
  	User author = new User();
  	if(checker.isEmpty()) {
  		throw new NotFoundException("Tweet with this id not found.");
  	}
  	for(User u: userRepository.findAll()) {
  		if(credentialsMapper.entityToDto(u.getCredentials()).equals(credentials) && u.isDeleted() == false){
  			author = u;
  		}
  	}
  	
  	if(author.getCredentials() == null) {
 		throw new NotAuthorizedException("Credentials are not correct.");
  	}
  	
  	Tweet checkerTweet = checker.get();
  	if(checkerTweet.isDeleted() == true)
  	{
  		throw new NotFoundException("Tweet with this id not found.");
  	}
  	if(tweetRequest.getContent() == null) {
  		throw new BadRequestException("Content needs to be filled in.");
  	}
  	
  	
 	String contenter = tweetRequest.getContent();
  	
  	System.out.println(contenter);
  	
  	int tracker = 0;
  	boolean starter = false;
  	
  	List<String> special = new ArrayList<>();
  	
  	for(int i = 0; i < contenter.length(); i++) {
  		if(contenter.charAt(i) == '#' || contenter.charAt(i) == '@') {
  			tracker = i;
  			starter = true;
  		}
  		else if(!Character.isLetter(contenter.charAt(i)) && contenter.charAt(i) != '_') {
  			if(starter == true) {
  				special.add(contenter.substring(tracker, i));
  				starter = false;
  			}
  			
  		}
  		
  	}
  	
  	if(starter == true) {
  		special.add(contenter.substring(tracker, contenter.length()));
  	}
  	
  	System.out.println(special);
  	

  	current.setAuthor(author);
  	current.setContent(tweetRequest.getContent());
  	current.setInReplyTo(checkerTweet);
  	
  	for(String u: special) {
  		if(u.charAt(0) == '#') {
  			Hashtag h = new Hashtag();
  			for(Hashtag hasher: hashtagRepository.findAll()) {
  				if(hasher.getLabel().equals(u.toLowerCase())) {
  					h = hasher;
  				}
  			}
  			if(h.getLabel() == null)
  			{
  				h.setLabel(u.toLowerCase());
  				h.getTweets().add(current);
  				hashtagRepository.saveAndFlush(h);
  			}
  		  	current.getHashtags().add(h);
  		}
  		else if(u.charAt(0) == '@') {
  			String label = u.substring(1, u.length());
  			for(User use: userRepository.findAll()) {
  				if(use.getCredentials().getUsername().equals(label) && use.isDeleted() == false) {
  					current.getMentionedUsers().add(use);
  				}
  			}
  		}
  	}
  	
  	return tweetMapper.entityToDto(tweetRepository.saveAndFlush(current));
  }
  
  @Override
  public TweetResponseDto postTweet(TweetRequestDto tweetRequest) {
  	Tweet current = new Tweet();
  	CredentialsDto credentials = tweetRequest.getCredentials();
  	
  	Optional<User> foundUser = userRepository.findByCredentials_Username(credentials.getUsername());
  	
  	if(foundUser.isEmpty()) {
  		throw new NotAuthorizedException("Credentials are empty.");
  	}
  	CredentialsDto dtoCredentials = credentialsMapper.entityToDto(foundUser.get().getCredentials());
  	System.out.println(dtoCredentials.getUsername());
  	System.out.println(credentials.getUsername());
 	System.out.println(dtoCredentials.getPassword());
  	System.out.println(credentials.getPassword());
  	if(!credentials.getUsername().equals(dtoCredentials.getUsername())  ||  !credentials.getPassword().equals(dtoCredentials.getPassword())) {
  		// compare two credentials 
 		throw new NotAuthorizedException("Credentials are not correct.");
  	}
  	
  	if(tweetRequest.getContent() == null) {
  		throw new BadRequestException("Content needs to be filled in.");
  	}

  	current.setAuthor(foundUser.get());
  	current.setContent(tweetRequest.getContent());
  	
  	return tweetMapper.entityToDto(tweetRepository.saveAndFlush(current));
  }
}