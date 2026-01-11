package Flyweight;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

interface CharacterFlyweight {
    public void draw(int x, int y);
}

class CharacterGlyph implements CharacterFlyweight {
    private final char symbol;
    private final String fontFamily;
    private final int fontSize;
    private final String color;

    public CharacterGlyph(char symbol, String fontFamily, int fontSize, String color) {
        this.symbol = symbol;
        this.fontFamily = fontFamily;
        this.fontSize = fontSize;
        this.color = color;
    }

    @Override
    public void draw(int x, int y) {
        System.out.println("Drawing '" + symbol + "' [Font: " + fontFamily +
                ", Size: " + fontSize + ", Color: " + color + "] at (" + x + "," + y + ")");
    }
}

class CharacterGlyphFactory {
    private final Map<String, CharacterGlyph> mp = new HashMap<>();

    public CharacterGlyph getFlyweight(char symbol, String fontFamily, int fontSize, String color) {
        String key = symbol + fontFamily + fontSize + color;
        mp.putIfAbsent(key, new CharacterGlyph(symbol, fontFamily, fontSize, color));
        return mp.get(key);
    }

    public int getFlyweightCount() {
        return mp.size();
    }
}

class TextEditorClient {
    private final CharacterGlyphFactory factory;
    private final List<RenderedCharacter> document = new ArrayList<>();

    public TextEditorClient(CharacterGlyphFactory factory) {
        this.factory = factory;
    }

    public void addCharacter(char symbol, int x, int y, String fontFamily, int fontSize, String color) {
        CharacterGlyph glyph = factory.getFlyweight(symbol, fontFamily, fontSize, color);
        document.add(new RenderedCharacter(glyph, x, y));
    }

    public void renderDocument() {
        for (RenderedCharacter rc : document) {
            rc.render();
        }
        System.out.println("Total flyweight objects used: " + factory.getFlyweightCount());
    }

    private class RenderedCharacter {
        private final CharacterFlyweight glyph;
        private final int x, y;

        public RenderedCharacter(CharacterFlyweight glyph, int x, int y) {
            this.glyph = glyph;
            this.x = x;
            this.y = y;
        }

        public void render() {
            glyph.draw(x, y);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        TextEditorClient client = new TextEditorClient(new CharacterGlyphFactory());
        String word = "Hello";
        for (int i = 0; i < word.length(); i++) {
            client.addCharacter(word.charAt(i), 10 + i * 15, 50, "Arial", 14, "#000000");
        }
        client.renderDocument();;
    }
}
