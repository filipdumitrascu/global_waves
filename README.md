# Global Waves - Audio Player

Global Waves is an audio player project with functionality similar to Spotify. It simulates various user interactions within an audio application. Multiple users can interact with the app simultaneously, with an admin managing their activities.

#### Contributor: Dumitrașcu Filip-Teodor

## Project Structure

### **src/**
This directory contains the core implementation of the project.

#### **checker/**
Contains checker files used for validation.

#### **fileio.input/**
Includes classes responsible for reading data from JSON files.

#### **main/**
- **Main**: Executes all commands in every test.
- **Test**: Runs only a subset of the tests.

#### **app/** - Core implementation:
- **audio/**: Stores all audio files.
- **data/**: Stores non-audio entities (varies based on user type).
- **pages/**: Implements a page system using the **Visitor Design Pattern**. The current page object calls the appropriate print method based on its type.
- **player/**: The audio file loader where listening commands can be executed.
- **searchBar/**: A filter-based search mechanism extended with the ability to search pages and load entities into players.
- **user/**: 
  - **UserEntry** class stores common attributes shared across all user types.
  - Users can store, modify, and interact with entities visible on their pages.
  - Normal users can load artist and host entities into their player.
- **utils/**:
  - **Admin**: Implements a **Singleton Design Pattern** to manage the entire application database and compute statistics based on system changes.
  - **CommandRunner**: Calls the appropriate method for each command and returns the output as an object node.

#### **input/**
Contains test cases and a library in JSON format.
