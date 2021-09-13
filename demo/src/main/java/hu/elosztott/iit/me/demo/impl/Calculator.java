package hu.elosztott.iit.me.demo.impl;

import org.springframework.stereotype.Service;

@Service
public class Calculator implements hu.elosztott.iit.me.demo.Calculator {

  @Override
  public Double add(Double operandus1, Double operandus2) {
    return operandus1 + operandus2;
  }
}
