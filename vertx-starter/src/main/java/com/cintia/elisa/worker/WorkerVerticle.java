package com.cintia.elisa.worker;

import io.vertx.core.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WorkerVerticle extends AbstractVerticle {

  private static final Logger LOG = LoggerFactory.getLogger(WorkerVerticle.class);

  @Override
  public void start(final Promise<Void> startPromise) throws InterruptedException {
    LOG.debug("Deployed as worker verticle");
    startPromise.complete();
    Thread.sleep(5000);
    LOG.debug("Blocking operation done");
  }
}
