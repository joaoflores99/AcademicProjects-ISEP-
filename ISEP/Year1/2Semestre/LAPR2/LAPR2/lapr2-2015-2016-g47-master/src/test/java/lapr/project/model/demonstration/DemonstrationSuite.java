/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model.demonstration;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author Renato Oliveira 1140822@isep.ipp.pt
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({lapr.project.model.demonstration.DemonstrationDecidedStateTest.class, lapr.project.model.demonstration.DemonstrationApplicationsInEvaluationStateTest.class, lapr.project.model.demonstration.DemonstrationDetectedConflictsStateTest.class, lapr.project.model.demonstration.DemonstrationInicialStateTest.class, lapr.project.model.demonstration.DemonstrationChangedConflictsStateTest.class, lapr.project.model.demonstration.DemonstrationApplicationsDecidedStateTest.class, lapr.project.model.demonstration.DemonstrationClosedAppplicationsStateTest.class, lapr.project.model.demonstration.DemonstrationOpenAppplicationsStateTest.class, lapr.project.model.demonstration.DemonstrationDiscontinuedStateTest.class, lapr.project.model.demonstration.DemonstrationApplicationsInDecisionStateTest.class, lapr.project.model.demonstration.DemonstrationCreatedStateTest.class})
public class DemonstrationSuite {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    
}
