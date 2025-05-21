# 🍒 Stick Hero Game – README

## 🎮 How to Play the Game

- **Hold and release** the **mouse** to extend the stick.
- **Click** the **mouse** once to flip the character onto the cherry.
- **Require more than 3 cherries** to revive the character after falling. 

---

## 🚀 Game Flow

### 🟢 Initial Scene
Upon launching the game, players are presented with the following options:
- **Start**
- **Exit**
- **Reload**

### 🕹️ Main Game Screen
Once started, the game features three major point systems:
- **Cherries** – Used to revive the character
- **Normal Points** 

The game also includes a **Pause Menu**:
- **Save and Exit**
- **Resume**

### 🔁 Game Over Scene
If the user undershoots or overshoots the stick, the character falls and is taken to a new scene with:
- **Restart**
- **Revive** (available only if cherries ≥ 3)
- **Main Menu**

---

## 🧠 Important Concepts Applied

- **Inheritance** – Used in the class hierarchy of the game.
- **Serialization and Deserialization** – For saving and loading game state.
- **Threading** – Maintains count for successful move streaks.
- **Singleton / Design Patterns** – Ensures a single instance in `Admin` and `StickHero` classes.
- **JUnit** – Used for testing game components.

---
