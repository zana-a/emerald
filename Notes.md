- Identifier introduced, so it can be tested.
- Options removed since repetitions do not return a none
- moduleIdentifier removed so that naming is more expressive
- moved to idea based project. ultimately abandoned for lack of library
  automation
- Moved back to scala 2 for better tooling
- in some cases the parser needs whitespace such as mod-A-do where `-` is the
  white space. Without it, parser thinks it is part of the previous token.
- Statement parser removed as there was no need for it

TODO

- perhaps make empty lists as some instead 
