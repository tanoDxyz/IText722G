


Overview
--------

Itext722g is an android port of popular java based pdf library known as IText. (https://github.com/itext/itext7)  
**currently version 7.2.2 is ported.**



Prerequisites
-------------

You may want to add 

```xml
<uses-permission
        android:name="android.permission.WRITE_EXTERNAL_STORAGE"
        android:maxSdkVersion="28" />
        
<uses-permission android:name="android.permission.INTERNET"/>
```
Also if you are going to add images or files to pdf from internet.  
 We need to add the Internet access permissions
in the Manifest.  
```xml
```

How to use IText722G
----------------

Using Itext722G is easy! Just add the following to your application's root build.gradle file.

```java
 allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
 }
```
And then add the dependency to the module level build.gradle file.
```java
implementation 'com.github.tanoDxyz:IText722G:v0.1'
```

### Initalization
just call before using the library
```java
IText722.init(Context) 
```


### Books and Tutorials 
https://itextpdf.com/resources/books/itext-7-building-blocks

https://itextpdf.com/resources/books/itext-7-jump-start-tutorial

https://kb.itextpdf.com/home/it7kb/examples

https://itextpdf.com/resources/books/best-itext-questions-stackoverflow




Little Implementation Details
------------------
java.awt.Image is replaced with android.graphics.bitmap.  
library  works well on android with api 19 or above.  
From styledXmlParser package ACCESS_EXTERNAL_DTD has been removed.  
BouncyCastle is replaced with SpongyCastle.  

### File Path
Library only understands absolute file paths. RandomAccessFile is used everywhere so if Scope storage is active please make sure that you have read/write permission on the paths or files passed to library. use SAF or other means.

### Adding Assets
If you place files in the app's asset directory or any sub directory inside assets - your file path should look like this  

original file placed = assets/fonts/fancyFont.otf

path passed to the library = fonts/fancyFont.otf

Raw Files: IText won't load files that resides in your raw directory.  

*Assets are loaded into memory*


### Tests
IText sources have test cases already written and for the following scenaiors and packages I run them on android.  
BarCodes ---- Ok.  
Basic PDF creation ----- Ok.  
Basic Pdf reading, ---- Ok.



Contribute
----------
IText722G can only get better if you make code contributions. Found a bug? Report it.
Have a feature idea you'd love to see in IText722G? Contribute to the project!

License
-------

```
Copyright (c) 1998-2022 iText Group NV

    This program is free software; you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License version 3
    as published by the Free Software Foundation with the addition of the
    following permission added to Section 15 as permitted in Section 7(a):
    FOR ANY PART OF THE COVERED WORK IN WHICH THE COPYRIGHT IS OWNED BY
    ITEXT GROUP. ITEXT GROUP DISCLAIMS THE WARRANTY OF NON INFRINGEMENT
    OF THIRD PARTY RIGHTS

    This program is distributed in the hope that it will be useful, but
    WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
    or FITNESS FOR A PARTICULAR PURPOSE.
    See the GNU Affero General Public License for more details.
    You should have received a copy of the GNU Affero General Public License
    along with this program; if not, see http://www.gnu.org/licenses or write to
    the Free Software Foundation, Inc., 51 Franklin Street, Fifth Floor,
    Boston, MA, 02110-1301 USA, or download the license from the following URL:
    http://itextpdf.com/terms-of-use/

    The interactive user interfaces in modified source and object code versions
    of this program must display Appropriate Legal Notices, as required under
    Section 5 of the GNU Affero General Public License.

    In accordance with Section 7(b) of the GNU Affero General Public License,
    a covered work must retain the producer line in every PDF that is created
    or manipulated using iText.

    You can be released from the requirements of the license by purchasing
    a commercial license. Buying such a license is mandatory as soon as you
    develop commercial activities involving the iText software without
    disclosing the source code of your own applications.
    These activities include: offering paid services to customers as an ASP,
    serving PDFs on the fly in a web application, shipping iText with a closed
    source product.

    For more information, please contact iText Software Corp. at this
    address: sales@itextpdf.com
```
