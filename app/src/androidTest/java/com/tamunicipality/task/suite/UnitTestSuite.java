package com.tamunicipality.task.suite;

import com.tamunicipality.task.InstrumentedTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by Dmitriy on 28.10.2017.
 */
// Runs all unit tests.
@RunWith(Suite.class)
@Suite.SuiteClasses({InstrumentedTest.class})
public class UnitTestSuite {
}
