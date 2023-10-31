package ru.pas_zhukov;

import ru.pas_zhukov.WrongGallowsStateIndexException;
import ru.pas_zhukov.GallowsFinalStateReachedException;

public final class Gallows {
    public static final String[] states = {
            "     _________\n    |         |\n    |          \n    |            \n    |            \n    |\n    |",
            "     _________\n    |         |\n    |         0\n    |            \n    |            \n    |\n    |",
            "     _________\n    |         |\n    |         0\n    |         |  \n    |            \n    |\n    |",
            "     _________\n    |         |\n    |         0\n    |        /|  \n    |            \n    |\n    |",
            "     _________\n    |         |\n    |         0\n    |        /|\\\n    |            \n    |\n    |",
            "     _________\n    |         |\n    |         0\n    |        /|\\\n    |        /   \n    |\n    |",
            "     _________\n    |         |\n    |         0\n    |        /|\\\n    |        / \\\n    |\n    |",
    };

    private int current_state;

    public Gallows() {
        this(0);
    }

    public Gallows(int stateIndex) {
        this.setState(stateIndex);
    }

    public int getState() {
        return current_state;
    }

    public void setState(int stateIndex) throws WrongGallowsStateIndexException {
        if (stateIndex < states.length && stateIndex >= 0) {
            this.current_state = stateIndex;
        }
        else {
            throw new WrongGallowsStateIndexException();
        }
    }

    public void nextState() throws GallowsFinalStateReachedException {
        if (this.current_state < states.length) {
            this.current_state++;
        }
        else {
            throw new GallowsFinalStateReachedException();
        }
    }

    public int getAttemptsCount() {
        return states.length - getState();
    }

    @Override
    public String toString() {
        return states[current_state];
    }
}

