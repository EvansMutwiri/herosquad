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

    @Test
    void NewHeroObjectGetsCorrectlyCreated_true() throws Exception {
        Hero post = new Hero("Hero Nakamora");
        assertEquals(true, post instanceof Hero);
    }

    @Test
    void NewHeroObjectInstantiatesWithContent_true() {
        Hero post = new Hero("Hero Nakamora");
        assertEquals("Hero Nakamora", post.getContent());
    }

    @AfterEach
    void tearDown() {
        Hero.clearAllHeros();
    }

    @Test
    public void AllHerosAreCorrectlyReturned_true() {
        Hero post = new Hero("Hero Nakamora");
        Hero otherHero = new Hero("Captain Ameruca");
        assertTrue(Hero.getAll().contains(post));
        assertTrue(Hero.getAll().contains(otherHero));
    }

    @Test
    public void AllHerosContainsAllHeros_true() {
        Hero post = new Hero("Hero Nakamura");
        Hero otherHero = new Hero("Captain Ameruca");
        assertTrue(Hero.getAll().contains(post));
        assertTrue(Hero.getAll().contains(otherHero));
    }

    @Test
    public void getPublished_isFalseAfterInstantiation_false() throws Exception {
        Hero myHero = new Hero("Hero Nakamura");
        assertEquals(false, myHero.getPublished());
    }

    @Test
    public void getCreatedAt_instantiatesWithCurrentTime_today() throws Exception{
        Hero myHero = setupNewHero();
        assertEquals(LocalDateTime.now().getDayOfWeek(), myHero.getCreatedAt().getDayOfWeek());
    }

    public Hero setupNewHero(){
        return new Hero("Hero Nakamura");
    }

    @Test
    public void getId_postsInstantiateWithAnID_1() throws Exception{
        Hero.clearAllHeros();
        Hero myHero = new Hero("Hero Nakamura");
        assertEquals(1, myHero.getId());
    }

    @Test
    public void findReturnsCorrectHero() throws Exception {
        Hero post = setupNewHero();
        assertEquals(1, Hero.findById(post.getId()).getId());
    }

    @Test
    public void findReturnsCorrectHeroWhenMoreThanOneHeroExists() throws Exception {
        Hero post = setupNewHero();
        Hero otherHero = new Hero("Captain Ameruca");
        assertEquals(2, Hero.findById(otherHero.getId()).getId());
    }

    @Test
    public void updateChangesHero() throws Exception {
        Hero post = setupNewHero();
        String formerContent = post.getContent();
        LocalDateTime formerDate = post.getCreatedAt();
        int formerId = post.getId();

        post.update("Some hero");

        assertEquals(formerId, post.getId());
        assertEquals(formerDate, post.getCreatedAt());
        assertNotEquals(formerContent, post.getContent());
    }
}