package ru.pas_zhukov;

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

    private int currentState;

    public Gallows() {
        this(0);
    }

    public Gallows(int stateIndex) {
        this.setState(stateIndex);
    }

    public int getState() {
        return currentState;
    }

    public void setState(int stateIndex) throws WrongGallowsStateIndexException {
        if (stateIndex < states.length && stateIndex >= 0) {
            this.currentState = stateIndex;
        }
        else {
            throw new WrongGallowsStateIndexException();
        }
    }

    public void nextState() throws GallowsFinalStateReachedException {
        if (this.currentState < states.length) {
            this.currentState++;
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
        return states[currentState];
    }
}

