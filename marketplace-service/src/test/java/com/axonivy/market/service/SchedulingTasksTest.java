package com.axonivy.market.service;

import com.axonivy.market.schedulingtask.ScheduledTasks;
import org.awaitility.Awaitility;
import org.awaitility.Durations;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;

@SpringBootTest(properties = { "MARKETPLACE_INSTALLATION_URL=D:/marketplace-installation.json" })
class SchedulingTasksTest {

  @SpyBean
  ScheduledTasks tasks;

  @Test
  void testShouldNotTriggerAfterApplicationStarted() {
    Awaitility.await().atMost(Durations.TEN_SECONDS).untilAsserted(() -> {
      verify(tasks, atLeast(0)).syncDataForProductFromGitHubRepo();
    });
  }
}
