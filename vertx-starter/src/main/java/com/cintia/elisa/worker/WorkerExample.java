package com.cintia.elisa.worker;

import io.vertx.core.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WorkerExample extends AbstractVerticle {

  private static final Logger LOG = LoggerFactory.getLogger(WorkerExample.class);

  public static void main(String[] args) {
    var vertx = Vertx.vertx();
    vertx.deployVerticle(new WorkerExample());
  }

  @Override
  public void start(final Promise<Void> startPromise) throws InterruptedException {
    vertx.deployVerticle(new WorkerVerticle(),
      new DeploymentOptions()
        .setWorker(true)
        .setWorkerPoolSize(1)
        .setWorkerPoolName("my-worker-verticle")
    );
    startPromise.complete();
    exceuteBlockingCode();
  }

  private void exceuteBlockingCode() {
    vertx.executeBlocking(event -> {
      LOG.debug("Start {}", getClass().getName());
      try {
        Thread.sleep(5000);
        event.complete();
//        event.fail("Force Fail!");
      } catch (InterruptedException e) {
        LOG.error("Failed: ", e);
        event.fail(e);
      }
    }, result -> {
      if (result.succeeded()) {
        LOG.debug("Blocking call done");
      } else {
        LOG.debug("Blocking call failed due to: ", result.cause());
      }
    });
  }
}
