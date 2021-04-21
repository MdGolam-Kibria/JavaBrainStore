# <h1><b> Advanced Java Interview Question with answers 🙋 </b></h1>

 <b> 1. What is Serialization and deserialization ?  <br/></b> 
      <b><u>Answer:- </u></b> <br/>
      <b><h6>Serialization :- </h6></b> <br/>
      <p>
      Serialization is a way of converting object into a byte stream.It is mainly used to transfer object's on the network.basically, it help us to use a object one Java Virtual machine to another VM. we know bytecode understandable for any OS and that's why we know java is a platform independent language.
  if we want to convert a object to byte stream, We must have to implement the Serializable interface for serializing the object.Serializable is a marker interface there is no member data or method and after that we can achieved this object by Deserialization <br/><br/>
  <b>use case id Serialization</b>:-<br/><br/>
       1) For Deep Communication.<br/>
       2) For Caching (The time consumed in building an object is more compared to the time required for de-serializing it) by this way we can optimize our application and much more .<br/>
       <br/>
       <b>Example :-</b> <br/><br/>
       - Serialization @https://github.com/MdGolam-Kibria/interviewQuestion/blob/master/src/main/java/com/CrackCode/interviewQuestion/SerializationDeserialization/Serialization.java
       
 <b><h6>Deserialization :- </h6></b> 
 If we talk about deserialization , deserialization  is way for achieved a object from  byte code by proper identity,  cause if JVM didn't find <b>serialVersionUID</b>
then it will show an error called <b>InvalidClassExcption</b>.<br/>

Full Example For Serialization and deserialization
@https://github.com/MdGolam-Kibria/interviewQuestion/tree/master/src/main/java/com/CrackCode/interviewQuestion/SerializationDeserialization/FullExample
       
       
       
       
    
