package com.cintia.elisa.verticles;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VerticleAB extends AbstractVerticle {

  public static final Logger LOG = LoggerFactory.getLogger(VerticleAB.class);
  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    LOG.debug("Start ", getClass().getName());
    startPromise.complete();
  }
}
