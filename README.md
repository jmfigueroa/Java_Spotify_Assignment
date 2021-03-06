# Spotify Lab Assignment
________

## Resources Used

### Java Objects
- [Java Programming Tutorial - 04 - Defining a Class and Creating Objects in
  Java](https://www.youtube.com/watch?v=4xKihjI6HJ0)
- [Java Programming Tutorial - 05 - Storing and Accessing Instance Variables of
  an Object](https://www.youtube.com/watch?v=q4vknQYjHy8)
- [StackOverflow: Check Object Empty](https://stackoverflow.com/a/14463338)

### Strings
- [Java String split() Method with examples](https://beginnersbook.com/2013/12/java-string-split-method-example/)
-
[StackOverflow: Splitting an array of strings in Java using .split](https://stackoverflow.com/questions/19431710/splitting-an-array-of-strings-in-java-using-split)
-
[StackOverflow: Split string with dot as
delimiter](https://stackoverflow.com/a/3387646) - b/c regex
- [Trim](https://www.geeksforgeeks.org/java-string-trim-method-example/)
### ArrayList
- [Get the size of an ArrayList in Java](https://www.tutorialspoint.com/get-the-size-of-an-arraylist-in-java#:~:text=The%20size%20of%20an%20ArrayList%20can%20be%20obtained%20by%20using,the%20ArrayList%20i.e.%20the%20size.)
- [How to iterate through an ArrayList of Objects of ArrayList of Objects](https://xspdf.com/resolution/58257776.html)

### Console Operations
- [StackOverflow - Java, clear console](https://stackoverflow.com/a/32295974)
________

## Pseudocode

Written in [Mermaid.js syntax](https://mermaid-js.github.io/mermaid/) which outputs the following/attached flowchart.


```
graph TD
    A[START] -->|validate user input| B{{GET user creds}}
    B --> C[(READ userAccount from file)]
    C --> D[IF authenticated]
    C --> |3 failed attempts| X[ELSE exit program]
    D --> E[DISPLAY nav menu]
    E --> F{{GET userNavSelection}}
    F --> |validate user input| G[IF View current plan]
    F --> |validate user input| H[ELSE IF modify current plan]
    F -.-> X
    G --> I[DISPLAY current plan]
    I --> J{{GET userAmount to spend}}
    J --> |validate user input| K[IF userAmount >= currentPlanPrice]
    K --> L[DISPLAY Preset 'awesome' message]
    L -.-> F
    J --> |validate user input| M[ELSE IF userAmount < currentPlanPrice]
    M --> N[DISPLAY Preset 'sub on hold' message]
    H --> O{{GET userPlanChange}}
        O --> |validate user input| R[UPDATE userAccount with new plan]
    R -.-> F
    O --> |validate user input| P[IF no change]
    O --> |validate user input| Q[IF 'student' plan]
    Q --> T{{GET userEmail}}
    T --> |validate user input| U[IF userEmail includes '.edu']
    U --> V[DISPLAY Preset Premium Student message]
    T --> |validate user input / 1 failed attempt| W[DISPLAY Preset 'not eligible' message]
    V -.-> F
    W -.-> U
    W -.-> F
    ```
    
  ![alt flowchart](./mermaid-diagram.png)
    
    
    
  ###  Mermaid Config
```
{
  "theme": "default"
}
```
