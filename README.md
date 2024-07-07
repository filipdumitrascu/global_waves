# Global Waves - Audio Player

#### Contributor: Dumitrașcu Filip-Teodor

## Project description

   Global waves is a project that has similar functionality to Spotify. It simulates different commands made by users within an audio file application.Thus, at any given moment in time several users can interact with the application and be managed by an admin.


## Project structure

### src/

#### checker/ 
    checker files
    
#### fileio.input/
    contains classes used to read data from the json files
    
#### main/
    Main - runs the specific command in every test.
    Test - runs only a part of tests.

#### app - the implementation:
    
    audio/ - where all the audio files are stored.
    data/ - where non audio entities are stored (which entities can be
            found here depends on the user type). 
    pages/ - the page system implemented with the visitor design pattern
            so the current page object calls the exact print page method
            according to its object type.
    player/ - the loader of audio files where can be performed
            listening commands.
    searchBar/ - filter-based search mechanism now extended with the option
                to search pages and load entities from them in players.
    user/ - knowing that every user has some attributes in common,
            UserEntry class stores and extends them to other user types.
            Each user stores entities and modifies them along the way.
            They are visible on each one's page and the normal user can
            load artist's and host's entities in their player.
    utils/
    * Admin - a singleton design pattern is used for an instance that
            stores the entire application database and calculates
            statistics according to changes that occur along the way.
    * CommandRunner - calls the specific method for every command and
                    returns in an object node the output for the command.

#### input/ - contains the tests and library in JSON format
