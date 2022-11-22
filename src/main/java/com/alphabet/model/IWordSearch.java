package com.alphabet.model;

import java.util.List;

public interface IWordSearch {
    
    public List<String> getWords();
    
    public boolean setWords(int startRow, int startColumn, int endRow, int endColumn);

}
