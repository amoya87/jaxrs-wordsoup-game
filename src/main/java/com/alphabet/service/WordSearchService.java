package com.alphabet.service;

import com.alphabet.model.IWordSearch;
import com.alphabet.model.Solve;
import com.alphabet.model.Soup;
import com.alphabet.model.WordSearch;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class WordSearchService implements IWordSearchService {
    
    private static Map<String, IWordSearch> puzzles = new HashMap<>();
    
    private static int puzzlesCount = 0;
    
    @Override
    public String addPuzzle(Soup s) {
        IWordSearch ws = new WordSearch(s.getW(), s.getH(), s.isLtr(), s.isRtl(), s.isTtb(), s.isBtt(), s.isD(), false,
                10);
        String id = UUID.randomUUID().toString();
        puzzles.put(id, ws);
        return id;
    }
    
    @Override
    public List<String> getPuzzleWords(String id) {
        return puzzles.get(id).getWords();
    }
    
    @Override
    public String getPuzzle(String id) {
        return puzzles.get(id).toString();
    }

    @Override
    public Boolean solvePuzzle(Solve s, String id) {
        // TODO Auto-generated method stub
        return puzzles.get(id).setWords(s.getSr(), s.getSc(), s.getEr(), s.getEc());
    }
    


}
