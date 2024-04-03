package com.cintia.elisa.eventbus;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class PublishSubscribeExample {

  public static void main(String[] args) {
    var vertx = Vertx.vertx();
    vertx.deployVerticle(new Publisher());
    vertx.deployVerticle(new Subscriber1());
    vertx.deployVerticle(Subscriber2.class.getName(), new DeploymentOptions().setInstances(2));
  }

  public static class Publisher extends AbstractVerticle {
    @Override
    public void start(final Promise<Void> startPromise) throws Exception {
      startPromise.complete();
      vertx.setPeriodic(Duration.ofSeconds(10).toMillis(), id -> {
        // Send a message every second
        vertx.eventBus().publish(Publisher.class.getName(), "Sending a message...");
      });
    }
  }

  public static class Subscriber1 extends AbstractVerticle {

    private static final Logger LOG = LoggerFactory.getLogger(Subscriber1.class);

    @Override
    public void start(final Promise<Void> startPromise) throws Exception {
      startPromise.complete();
      vertx.eventBus().<String>consumer(Publisher.class.getName(), message -> LOG.debug("Received: " + message.body()));
    }
  }

  public static class Subscriber2 extends AbstractVerticle {

    private static final Logger LOG = LoggerFactory.getLogger(Subscriber2.class);

    @Override
    public void start(final Promise<Void> startPromise) throws Exception {
      startPromise.complete();
      vertx.eventBus().<String>consumer(Publisher.class.getName(), message -> LOG.debug("Received: " + message.body()));
    }
  }
}
