package br.ufpa.refactoring.q4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RecentFilesTest {

    @Test
    void addFile_shouldKeepMostRecentFirst_andRespectLimit() {
        RecentFiles rf = new RecentFiles(3);

        rf.addFile("/a");
        rf.addFile("/b");
        rf.addFile("/c");
        rf.addFile("/d");

        assertEquals(3, rf.size());
        assertEquals("/d", rf.get(0));
        assertEquals("/c", rf.get(1));
        assertEquals("/b", rf.get(2));
    }

    @Test
    void refactorGoal_replaceInheritanceWithDelegation() {
       // Garante que RecentFiles NÃO estende ArrayList (Herança substituída por Delegação)
        assertNotEquals(ArrayList.class, RecentFiles.class.getSuperclass(), 
            "RecentFiles não deve estender ArrayList diretamente; deve usar delegação.");
    }
}
