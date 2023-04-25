/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.group_one.expresscart;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Class for testing the SceneFactory class.
 * 
 * @author Group 1
 */
public class SceneFactoryTest {

    public SceneFactoryTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }
    
    /**
     * Test of Scene Factory Constructor, of class SceneFactory.
     */
    @Test
    public void testSceneFactoryContructor() {
        System.out.println("SceneFactory->Contructor");
        SceneFactory SceneFactory1 = SceneFactory.getInstance();
        SceneFactory SceneFactory2 = SceneFactory.getInstance();
        SceneFactory SceneFactory3 = SceneFactory.getInstance();
        assertEquals(SceneFactory1, SceneFactory2);
        assertEquals(SceneFactory1, SceneFactory3);
    }
}
