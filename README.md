# Content-aware-image-resizing-algorithm

Designed and developed an implementation of [seam craving algorithm](https://en.wikipedia.org/wiki/Seam_carving) in Java. 

Seam craving is a content-aware image resizing algorithm. It iteratively removes pixel paths ("seams") to change an image's dimension while preserving imporant content. 

The implementation of algorithm on itself is pretty straightforward, however, simplest implementation performs in O(n^2). 

The design provided performs in O(n).
