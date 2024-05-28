package com.zeraki.zeraki.LogicTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import com.zeraki.zeraki.Entities.UserProgress;
import com.zeraki.zeraki.services.UserProgressService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LogicTest {
    private UserProgressService userProgressService;

    @BeforeEach
    public void setUp() {
        userProgressService = new UserProgressService() {
            @Override
            public List<UserProgress> findAllUserProgress() {
                return null;
            }

            @Override
            public List<UserProgress> findAllByUserId(Long id) {
                return null;
            }
        };
    }

    @Test
    public void testAddRemarks_poor() {
        List<UserProgress> userProgressList = new ArrayList<>();
        userProgressList.add(new UserProgress(40));
        userProgressList.add(new UserProgress(45));
        userProgressList.add(new UserProgress(50));

        List<UserProgress> result = userProgressService.findAllByUserId(353L);

        for (UserProgress userProgress : result) {
            assertEquals("poor", userProgress.getRemarks());
        }
    }

    @Test
    public void testAddRemarks_fair() {
        List<UserProgress> userProgressList = new ArrayList<>();
        userProgressList.add(new UserProgress(51));
        userProgressList.add(new UserProgress(55));
        userProgressList.add(new UserProgress(60));

        List<UserProgress> result = userProgressService.findAllByUserId(352L);

        for (UserProgress userProgress : result) {
            assertEquals("Fair", userProgress.getRemarks());
        }
    }

    @Test
    public void testAddRemarks_good() {
        List<UserProgress> userProgressList = new ArrayList<>();
        userProgressList.add(new UserProgress(61));
        userProgressList.add(new UserProgress(65));
        userProgressList.add(new UserProgress(70));

        List<UserProgress> result = userProgressService.findAllByUserId(353L);

        for (UserProgress userProgress : result) {
            assertEquals("Good", userProgress.getRemarks());
        }
    }

    @Test
    public void testAddRemarks_veryGood() {
        List<UserProgress> userProgressList = new ArrayList<>();
        userProgressList.add(new UserProgress(71));
        userProgressList.add(new UserProgress(75));
        userProgressList.add(new UserProgress(80));

        List<UserProgress> result = userProgressService.findAllUserProgress();

        for (UserProgress userProgress : result) {
            assertEquals("Very Good", userProgress.getRemarks());
        }
    }

    @Test
    public void testAddRemarks_excellent() {
        List<UserProgress> userProgressList = new ArrayList<>();
        userProgressList.add(new UserProgress(81));
        userProgressList.add(new UserProgress(90));
        userProgressList.add(new UserProgress(100));

        List<UserProgress> result = userProgressService.findAllByUserId(353L);

        for (UserProgress userProgress : result) {
            assertEquals("Excellent", userProgress.getRemarks());
        }
    }

    @Test
    public void testAddRemarks_outOfRange() {
        List<UserProgress> userProgressList = new ArrayList<>();
        userProgressList.add(new UserProgress(39));
        userProgressList.add(new UserProgress(101));

        List<UserProgress> result = userProgressService.findAllUserProgress();

        for (UserProgress userProgress : result) {
            assertEquals(null, userProgress.getRemarks());
        }
    }
}
