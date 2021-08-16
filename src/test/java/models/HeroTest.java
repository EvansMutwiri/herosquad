package models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class HeroTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
        Hero.clearAllHeroes();
    }
    @Test
    public void Hero_instantiateCorrectly_true(){
        Hero hero = new Hero("boss","1","fire","temper");
        assertTrue( hero instanceof Hero);
    }

}