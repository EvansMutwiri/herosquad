package models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SquadTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {

    }
    @Test
    public void squadInstantiatesCorrectly_true(){
        Squad squad = new Squad("fantastic 4", "save the world", 4);
        assertTrue(squad instanceof Squad);
    }
    @Test
    public void squadInstantiatesNameAs_String() {
        Squad squad = new Squad("fantastic 4", "save the world", 4);
        assertEquals("fantastic 4", squad.getName());
    }

    @Test
    public void squadInstantiatesCause_String() {
        Squad squad = new Squad("fantastic 4", "save the world", 4);
        assertEquals("save the world", squad.getCause());
    }

    @Test
    public void squadInstantiatesWithMax_int() {
        Squad squad = new Squad("fantastic 4", "save the world", 4);
        assertEquals(4, squad.getMaximum());
    }

    @Test //getAll
    public void squadReturnsAll_instancesOfSquad_true() {
        Squad squad = new Squad("fantastic 4", "save the world", 4);
        Squad aSquad = new Squad("hero squad", "protect earth", 2);
        assertEquals(true, squad.getAll().contains(squad));
        assertEquals(true, squad.getAll().contains(aSquad));
    }

    @Test  //
    public void squad_clearsAllInstances_0() {
        Squad aSquad = new Squad("hero squad", "protect earth", 2);
        Squad.clearAllSquad();
        assertEquals(0, Squad.getAll().size());
    }

    @Test
    public void squadInstantiatesWith_Id_1() {
        Squad.clearAllSquad();
        Squad squad = new Squad("fantastic 4", "save the world", 4);
        assertEquals(1, squad.getId());
    }

    @Test
    public void findById_squadTwo() {
        Squad squad = new Squad("fantastic 4", "save the world", 4);
        Squad aSquad = new Squad("hero squad", "protect earth", 2);
        assertEquals(Squad.findById(aSquad.getId()), aSquad);
        assertEquals(Squad.findById(squad.getId()), squad);
    }

    @Test
    public void getHeroes() {
        Squad squad = new Squad("fantastic 4", "save the world", 4);
        assertEquals(0, squad.getHeroes().size());
    }

    @Test
    public void addHero() {
        Squad squad = new Squad("fantastic 4", "save the world", 4);
        Hero post = new Hero("Hero Nakamura");
        squad.addHero(post);
        assertEquals(true, squad.getHeroes().contains(post));
    }
}