# rcml
### Raven's Component Markup Language
RCML is a markup language for paper components, i made this because i found them tideous to write by hand

`[color:0,255,17]Colored text`<br>
`[italic:true]Italic text`<br>
`[underlined:true]Underlined text`<br>
`[bold:true]Bold text`<br>
`[strikethrough:true]Strikethrough text`<br>
`[color:0,255,17][bold:true]Combine tags!`<br>
`Brackets can be used like this: [[ ]]`<br>

## Tags
- `color` 
  - Description: Set text color for the following text
  - Value: RGB color, e.g. 123,255,255 or 123, 233, 144
- `italic`
  - Description: Set italic flag for the following text
  - Value: true|false 
- `underlined`
  - Description: Set underlined flag for the following text
  - Value: true|false 
- `bold`
  - Description: Set bold flag for the following text
  - Value: true|false 
- `strikethrough`
  - Description: Set strikethrough flag for the following text
  - Value: true|false 
- `reset`
  - Description: Reset style 
  - Value: (No value)

## Errors
- `RCML01` - Unknown tag name
- `RCML02` - Invalid tag value
- `RCML03` - Unmatched tag value 
- `RCML04` - Invalid integer
- `RCML05` - Value found in a non-valued tag

## Setup (only maven for now)
- 1. Add jitpack repository
In your pom.xml in `<repositories>`:
```xml
<repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
</repository>
```
- 2. Add RCML dependency
In your pom.xml in `<dependencies>`:
```xml
<dependency>
    <groupId>com.github.aceinetx</groupId>
    <artifactId>rcml</artifactId>
    <version>1.3.7</version>
    <scope>provided</scope>
</dependency>
```
## Use
- 1. Import rcml
```java
import ru.aceinet.rcml.RCML;
```
- 2. Compile some rcml code
```java
Bukkit.getConsoleSender().sendMessage(
  RCML.compile("Hello from [bold:true][color:255,0,0]RCML[reset]!")
);
```

___

rvnfml (a.k.a aceinetx) 2021-2025