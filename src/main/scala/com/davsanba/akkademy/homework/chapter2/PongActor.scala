package com.davsanba.akkademy.homework.chapter2

import akka.actor.{Actor, Status}

class PongActor extends Actor {
  override def receive: Receive = {
    case "Ping" => sender() ! "Pong"
    case _ => sender() ! Status.Failure(new Exception("unknown message"))
  }
}