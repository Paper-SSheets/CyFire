package com.mockito.tests;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import com.cyfire.likes.LikeController;
import com.cyfire.likes.LikeRepository;
import com.cyfire.likes.Likes;

public class jaredsMockitoTests {

	
	// ***** JAREDS TESTS *****
		@InjectMocks
		LikeController likeControllerMock;
		
		@Autowired
		@Mock
		LikeRepository likeRepo;
		
		@Before
		public void init()
		{
			MockitoAnnotations.initMocks(this);
		}
		
		
		@Test
		public void getAllLikesTest()
		{
			List<Likes> likesList = new ArrayList<Likes>();
			Likes likeOne = new Likes("jweiland", "smsheets,aMarek");
			Likes likeTwo = new Likes("smsheets", "jweiland,aMarek");
			Likes likeThree = new Likes("aMarek", "jweiland,smsheets");
			
			likesList.add(likeOne);
			likesList.add(likeTwo);
			likesList.add(likeThree);
			
			when(likeRepo.findAll()).thenReturn(likesList);
			
			List<Likes> theLikes = likeControllerMock.getAllLikes();
			
			assertEquals(3, theLikes.size());
			verify(likeRepo, times(1)).findAll();
			
		}
		
		@Test
		public void getByNetIdTest()
		{
			List<Likes> likesList = new ArrayList<Likes>();
			Likes likeOne = new Likes("jweiland", "smsheets,aMarek");
			Likes likeTwo = new Likes("smsheets", "jweiland,aMarek");
			Likes likeThree = new Likes("aMarek", "jweiland,smsheets");
			
			likesList.add(likeOne);
			likesList.add(likeTwo);
			likesList.add(likeThree);
			
			when(likeRepo.findAll()).thenReturn(likesList);
			
			Likes like = likeControllerMock.getByNetId("jweiland");
			
			assertEquals("jweiland", like.getNet_id());
			assertEquals("smsheets,aMarek",like.getLikes());
		}
		
		
		@Test
		public void saveLikeTest()
		{
			Likes testLike = new Likes("jweiland", "aMarek,smsheets");
			String expected = "Like saved for: jweiland: aMarek,smsheets";
					
			
			when(likeRepo.save(testLike)).thenReturn(new Likes("jweiland","aMarek,smsheets"));
			
			
			String result = likeControllerMock.addNewLike(testLike);
			
			assertEquals(expected, result);
			
		}
		
		
		
}
