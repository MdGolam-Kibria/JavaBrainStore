package com.CrackCode.interviewQuestion.SerializationDeserialization;

import java.io.Serializable;

public class Serialization implements Serializable {
    private static final long serialVersionUID = 1L; //Iit must be static long final.
    private transient String city;//here {transient}  for hide Serializable mechanism.
    /**
     Here serialVersionUID :
     here each serializable class a version number, called a serialVersionUID ..here each serializable class a version number, called a serialVersionUID ..that actually give a instruction to JVM Identity of a class.if we loaded  the converted byte object by deserialization with different serialVersionUID  it will give an exception,  cause this serialVersionUID version code is a identity of a class.
     **/
}
