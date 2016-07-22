# Project Tasks  

Everything listed here is what I contributed to bounce application.  

Implement classes Oval, Gem, DynamicRectangle and OvalAndRectangle Shape
--
This was achieved by using test driven development, by completing classes TestShape and 
TestDynamicRectangleeShape to make sure new code didn't break existing code. Applying 
design patterns appropriately e.g. use of the template method pattern in DynamicRectangleShape. 
And applying OO principles correctly such as the single resposibility principle, encapsulation, 
abstraction and polymorphism.  

Introduce classes NestingShape and the ability for any Shape to display text
--
NestingShape added the ability for shapes to bounce within other shapes and created a recursive
structure for the shape model. Class NestingShape was implemented using the composite pattern.
The ability for any shape to display text was implemented using the template method pattern in
the Shape classes Paint() method. This allowed any Shape to display text provided it had the appropriate
constructor or a setText() method. This could have been done using the decorator pattern.  

Implement classes Task1, Task2 and ImageShapeFormHandler
--
Task1 uses the adapter pattern to make the ShapeModel compatible with Swings TreeModel
allowing a tree view of the Shape Model to be displayed within the application.
Task2 is an extension of Task1 and plays the role of a view/listener of the ShapeModel and notifies
the TreeModel when there are changes within the Shape Model (i.e. when the user adds/deletes a shape).
ImageShapeFormHandler introduces multithreading via SwingWorker to process an image on a background thread
when a ImageRectangleShape is added so that the GUI doesn't freeze.  

Implement Swings pluggable look and feel
--
This was done in the bounce.bounceApp.bounce class. I simply introduced a combobox and event handling
to change the look and feel of the application via Swings UIManager.

