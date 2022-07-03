Itext722g is an android port of popular java based pdf library known as IText. (https://github.com/itext/itext7)
currently version 7.2.2 is ported.


Implementation Details
 All java.awt.Image objects are replaced with android.graphics.bitmap.
 it works well on android with api 19 or above.
 From styledXmlParser package ACCESS_EXTERNAL_DTD has been removed.
 BouncyCastle is replaced by SpongyCastle.



Books on Itext

https://itextpdf.com/resources/books/itext-7-building-blocks

https://itextpdf.com/resources/books/itext-7-jump-start-tutorial

https://kb.itextpdf.com/home/it7kb/examples

https://itextpdf.com/resources/books/best-itext-questions-stackoverflow



Usage Details

Make sure call 
IText722.init(this); 
per app instance.

File Paths:
Library only understands absolute file paths.
RandomAccessFile is used everywhere so if Scope storage is active please make sure that you have read/write permission on the paths or files passed to library.
use SAF or other means.

Assets:
if you place files in the app's asset directory or any sub directory inside assets - your file path should look like this

original file placed = assets/fonts/fancyFont.otf

path to the library = fonts/fancyFont.otf


Raw Files:
IText won't load files that resides in your raw directory.




