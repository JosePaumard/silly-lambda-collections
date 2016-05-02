## Lambda Collections

This is the repository of my university talk for Devoxx France 2016, about collections performance, CPU structure and pointer chasing. The slides (in french) are here: The slides are here: http://fr.slideshare.net/jpaumard/arraylist-et-linkedlist-sont-dans-un-bateau. 

This code shows how it is possible to implement List, Set and Map from the JDK using lambda expressions, to a certain extent. These implementations (in fact they are not!) are immutable and can only support small collections and maps, holding a only a few elements or a few key / value pairs. They follow the Java 9 guidelines: no null elements are allowed, duplicate in sets should raise exceptions, and duplicate keys in maps should also raise exceptions. 

This should be taken as an example of what is possible to do with lambda and non-common use cases rather than an example on how to implement collections!

Thanks to Stuart Marks and Rémi Forax for their valuable ideas in improving this implementation, and for pointing out that it does not respect the equals() / hashCode() contracts of the Collection framework in many places. 

My advice: do not use these implementations in a production environment. If you choose not to do so, remember that you use this material at your own risk.  