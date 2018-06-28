package com.davsanba.akkademy.homework.chapter1

import akka.actor.Actor
import akka.event.Logging

class LastStringActor extends Actor {
  var lastString = Option.empty[String]
  val log = Logging(context.system, this)
  override def receive = {
    case string: String => {
      log.info("received String: {}", string)
      lastString = Some(string)
    }
    case o => log.info("received unknown message: {}", o);
  }
}