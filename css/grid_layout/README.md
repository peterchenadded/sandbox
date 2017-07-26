# Useful links

* <https://css-tricks.com/snippets/css/complete-guide-grid/>
* <https://developer.mozilla.org/en-US/docs/Web/CSS/CSS_Grid_Layout>
* <http://htmlshell.com/>

# Key terms

1. Grid Container: an element on which `display: grid` is applied.
2. Grid Item: a children (direct descendants) of the grid container.
3. Grid Line: dividing lines that make up the structure of the grid.
4. Grid Track: The space between two adjacent grid lines.
5. Grid Cell: The space between two adjacent row and two adjacent column grid lines.
6. Grid Area: The total space surrounded by four grid lines.

# Basic example

```
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <title>title</title>
    <style type="text/css">
      .wrapper {
          display: grid;
          grid-template-columns: repeat(3, 1fr);
          grid-gap: 10px;
          grid-auto-rows: minmax(100px, autho);
      }
      .one {
          grid-column: 1/3;
          grid-row: 1;
      }
      .two {
          grid-column: 2/4;
          grid-row: 1/3;
      }
      .three {
          grid-column: 1;
          grid-row: 2/5;
      }
      .four {
          grid-column: 3;
          grid-row: 3;
      }
      .five {
          grid-column: 2;
          grid-row: 4;
      }
      .six {
          grid-column: 3;
          grid-row: 4;
      }
    </style>
  </head>
  <body>
    <div class="wrapper">
        <div class="one">One</div>
        <div class="two">Two</div>
        <div class="three">Three</div>
        <div class="four">Four</div>
        <div class="five">Five</div>
        <div class="six">Six</div>
    </div>
  </body>
</html>
```
