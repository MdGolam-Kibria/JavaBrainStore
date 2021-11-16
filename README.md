# <h1><b> Advanced Java Interview Question with answers 🙋 </b></h1>

 <b>1) What is Serialization and deserialization ? <br/></b> 
      <b><u>Answer:- </u></b> <br/>
     @https://github.com/MdGolam-Kibria/interviewQuestion/tree/master/src/main/java/com/CrackCode/interviewQuestion/SerializationDeserialization <br/>
     
 <b>2)Polymorphism in java ? How can we achieve Polymorphism  ?<br/></b> 
 <b><u>Answer:- </u></b> <br/>
 @https://github.com/MdGolam-Kibria/interviewQuestion/tree/master/src/main/java/com/CrackCode/interviewQuestion/Polymorphism <br/>

 <b>4) What About Singleton pattern in java ? Types of singleton ? and How can we achieve that ? <br/></b> 
      <b><u>Answer:- </u></b> <br/>
     @https://github.com/MdGolam-Kibria/interviewQuestion/tree/master/src/main/java/com/CrackCode/interviewQuestion/designPattern/SingletonPattern <br/>
 
 
 <b>5) Difference  between factory and abstract factory pattern ? <br/></b> 
      <b><u>Answer:- </u></b> <br/><h6><u>Factory Pattern</u> </h6><br/>
      @https://github.com/MdGolam-Kibria/interviewQuestion/tree/master/src/main/java/com/CrackCode/interviewQuestion/designPattern/Factory <br/>
      <h6><u>Abstract Factory Pattern</u> </h6><br/>
      @https://github.com/MdGolam-Kibria/interviewQuestion/tree/master/src/main/java/com/CrackCode/interviewQuestion/designPattern/abstractFactory <br/>
      
      
<b>6) What is the Prototype Pattern ? <br/></b> 
      <b><u>Answer:- </u></b> <br/><h6><u>Prototype Pattern :-</u> </h6><br/>
      @https://github.com/MdGolam-Kibria/interviewQuestion/tree/master/src/main/java/com/CrackCode/interviewQuestion/designPattern/Prototype <br/>
      
      
 <b>7) What is the Builder Pattern ? <br/></b> 
      <b><u>Answer:- </u></b> <br/><h6><u>Builder Pattern :-</u> </h6><br/>
      @https://github.com/MdGolam-Kibria/interviewQuestion/tree/master/src/main/java/com/CrackCode/interviewQuestion/designPattern/BuilderPattern <br/>
      
      
 8) <h4>🔥🔥🔥 Java 8 All features like everything about [<b>🦸‍♂️Stream API</b>, <b>🦸‍♂️Optional class</b>, <b>🦸‍♂️Functional Interface</b>, <b>🦸‍♂️lambda</b>,<b>🦸‍♂️Predicate</b>] 🔥🔥🔥🔥</h4><br/>
      <b><u>Answer:- </u></b> <br/><h6><u>Everything About JAVA 8 :-</u> </h6><br/>
      @https://github.com/MdGolam-Kibria/interviewQuestion/tree/master/src/main/java/com/CrackCode/interviewQuestion/java8 <br/>

  <b>9) Difference JPA and JDBC with explain details with example.<br/></b>
     <b><u>Answer:- </u></b> <br/><h6><u>JPA and JDBC :-</u> </h6> 
     @https://www.baeldung.com/jpa-vs-jdbc
     
  <b>10) Convert Sql result array to expected JSON Object using <b>Reflection API </b> 🔥 <br/></b>
     <b><u>Answer:- </u></b> <br/><h6><u>JPA and JDBC :-</u> </h6> 
     ![image](https://user-images.githubusercontent.com/61331272/141931760-6301ad9f-3672-44e6-9003-f094fc9ad145.png)
     </b>
     
     ```
     /**
     * Common method for getData from database using entity Manager
     */
    @Service
    public class EntityManagerQueryHelper {

    @PersistenceContext
    private EntityManager entityManager;


    public Collection<Object> getQueryResult(String sql, Object expectedConvertedPojoClass) {
        Query query = entityManager.createNativeQuery(sql);
        /**
         * here expectedConvertedPojoClass model must look like the [query.getResultList()] value serial
         */
        return convertToModelObject(query.getResultList(), expectedConvertedPojoClass);
    }

    public Collection<Object> convertToModelObject(List resultList, Object expectedConvertedPojoClass) {//expectedConvertedPojoClass property should be same as your( sql output) serial value
        try {

            List<Object> objects = new ArrayList<>();
            for (int o = 0; o < resultList.size(); o++) {
                Field[] allPro = expectedConvertedPojoClass.getClass().getDeclaredFields();
                Map<Object, Object> properties = new HashMap<>();
                for (int i = 0; i < allPro.length; i++) {
                    properties.put(expectedConvertedPojoClass.getClass().getDeclaredFields()[i].getName(), ((Object[]) resultList.get(o))[i]);
                }
                objects.add(properties);
            }
            return objects;
        } catch (Exception e) {
            return null;
        }
    }
    }
     ```




</b>
# <h1><b><center> <b><u>About Multithreading / Concurrency programming in Java ?🙋</u></b></center> </b></h1>


<b>1) What is Multithreading in java ?.<br/></b>
<b><u>Answer:- </u></b> <br/><h6><u>Multithreading  :-</u> </h6>
 <p>
    <b><u>MULTITHREADING</u></b> in Java is a process of executing two or more threads simultaneously to maximum utilization of CPU. Multithreaded applications execute two or more threads run concurrently. Hence, it is also known as Concurrency in Java. Each thread runs parallel to each other. Mulitple threads don't allocate separate memory area, hence they save memory. Also, context switching between threads takes less time.
</p>

<b>2) Create a Thread and assign work to the thread after that get result from the thread ?.<br/></b>
<b><u>Answer :- </u></b> <br/><h6><u>Thread  :-</u> </h6>
 <p>
    @https://github.com/MdGolam-Kibria/interviewQuestion/blob/master/src/main/java/com/CrackCode/interviewQuestion/threadProgramming/getResultFromAnotherThread/GetResultFromAnotherThread.java
</p>

<b>3) Create two Thread and assign work to the threads after that get result from the thread ? Remember that second thread shouldn't workable before end first thread work.<br/></b>
<b><u>Answer :- </u></b> <br/><h6><u>Multithreading :-</u> </h6>
<p>
    @https://github.com/MdGolam-Kibria/interviewQuestion/blob/master/src/main/java/com/CrackCode/interviewQuestion/threadProgramming/multipleThread/MultipleThreadTest.java
</p>

<b>4)Create two or more thread that should be work simultaneously <br/></b>
<b><u>Answer :- </u></b> <br/><h6><u>Multithreading :-</u> </h6>
<p>
    @https://github.com/MdGolam-Kibria/interviewQuestion/blob/master/src/main/java/com/CrackCode/interviewQuestion/threadProgramming/multipleThread/MultipleThreadWorkTogetherTest.java
</p><br/>


<h2> 💯  Learn about PL/SQL 💯  </h2><br/>
<b>1)Create PL/SQL operation for get all data from a table and print some specific value<br/></b>
<b><u>Answer :- </u></b> <br/><h6><u>Answer:-</u> </h6>
<p>
    
![image](https://user-images.githubusercontent.com/61331272/140168729-dacdfedc-e961-4758-8ed2-c7926d85e01f.png)

</p><br/><br/>


<b>2)Create a procedure for insert data to a table<br/></b>
<b><u>Answer :- </u></b> <br/><h6><u>Answer:-</u> </h6>
<p>
    
![image](https://user-images.githubusercontent.com/61331272/140170070-a47c9d32-fab2-4032-a631-018d96c9f53a.png)
 <p>Another one Example:-</p><br/>
 
 ![image](https://user-images.githubusercontent.com/61331272/140170513-d637c3f8-f214-48ad-8ab0-66928c4fc3a1.png)
 
</p><br/>


<b>3)How to call a procedure for a spring  application<br/></b>
<b><u>Answer :- </u></b> <br/><h6><u>Answer:-</u> </h6>
<p>
    
 ![image](https://user-images.githubusercontent.com/61331272/140171122-ac0c8c75-ced8-41c8-8d90-6c509217f395.png)

</p><br/><br/>
      
      
<b>3)Create a procedure for get all data from a table ? and call it from spring boot server<br/></b>
<b><u>Answer :- </u></b> 
<br/><h6><u>Procedure:-</u> </h6>
   ![image](https://user-images.githubusercontent.com/61331272/140188207-265fbb62-033c-4abb-8205-c4d91dcb19a1.png)
 <br/>
 <br/><h6><u>Call from java :-</u> </h6><br/>
 
 ![image](https://user-images.githubusercontent.com/61331272/140188447-47cc3c73-70e1-4917-bbb2-afd1df43f14c.png)
 
 <b>4)Create a procedure for get <b>XML</b> as IN param and parse it after that save data to a table ? and call it from spring boot server by sending the expected params<br/></b>
<b><u>Answer :- </u></b> 
<br/><h6><u>Procedure:-</u> </h6>
   
 ```
 create procedure saveEmployeeex(
    stmt IN CLOB,
    output OUT number
) AS
    id_T    EMPLOYEE.ID%TYPE;
    name_T  EMPLOYEE.NAME%TYPE;
    email_T EMPLOYEE.EMAIL%TYPE;
    tempRow number;
    CURSOR field_cursor
        IS
        SELECT XMLTYPE.EXTRACT(VALUE(a),
                               '/rowrecord/STUDENT_ID/text()').getStringVal(),
               XMLTYPE.EXTRACT(VALUE(a),
                               '/rowrecord/STUDENT_NAME/text()').getStringVal(),
               XMLTYPE.EXTRACT(VALUE(a), '/rowrecord/STUDENT_EMAIL/text()').getStringVal()

        FROM TABLE (
                 XMLSEQUENCE(
                         XMLTYPE(stmt).EXTRACT('/statement/rowrecord'))) a;
begin

    output := 0;
    tempRow:=0;
    open field_cursor;

    LOOP
        FETCH field_cursor
            INTO id_T,name_T,email_T;
        exit when field_cursor%NOTFOUND;
    END LOOP;
    select count(*) into tempRow from EMPLOYEE where ID = id_T;
    IF tempRow is not null then
        update EMPLOYEE
        set EMPLOYEE.ID    = id_T,
            EMPLOYEE.NAME  = name_T,
            EMPLOYEE.EMAIL =email_T
        where ID = id_T;
        output := 1;
    else
        insert into EMPLOYEE(id, name, email) VALUES (id_T, name_T, email_T);
        commit;
        output := 1;
    end if;
END 
 ```
  
  
 <br/><h6><u>Call from java :-</u> </h6><br/>

![image](https://user-images.githubusercontent.com/61331272/140409532-cc580a34-106d-4d9c-b6f9-ed0d10a79358.png)

      
<b>5)Create a procedure inside a <b> package using PL?SQL</b> ? and call it from spring boot server<br/></b>
<b><u>Answer :- </u></b> 
<br/><h6><u>Procedure:-</u> </h6>
   For This case we need to create a package with procedure interface and after that create the procedure body using PL/SQ query as like below : - <br/>
  
  i)Create Package :-<br/>
![image](https://user-images.githubusercontent.com/61331272/140702489-bf05d732-fe2d-4c4b-a331-a77960cc60c2.png) <br/>
```
create PACKAGE getAllEmployeeByPackage AS
    PROCEDURE getAll(
        e_disp OUT SYS_REFCURSOR
    );
END getAllEmployeeByPackage;
/
```
  ii) create package body :-<br/>
![image](https://user-images.githubusercontent.com/61331272/140702622-bda1cdd4-014b-4ea6-8031-3b12897950c4.png) <br/>
```
create package body getAllEmployeeByPackage as
    procedure getAll(
        e_disp OUT SYS_REFCURSOR
    ) IS
    BEGIN
        open e_disp for select *  from EMPLOYEE;
    END getAll;
end getAllEmployeeByPackage;
/
```
 <br/>
 <br/><h6><u>Call from java :-</u> </h6><br/>
 
 ![image](https://user-images.githubusercontent.com/61331272/140702767-83d35fa5-18b6-4de5-b4df-395a2e54c238.png)
 
 
 <b>6)Create a procedure for get Employee by id otherwise retun empty sys_refcursor from EMPLOYEE table ? and call it from spring boot server<br/></b>
<b><u>Answer :- </u></b> 
<br/><h6><u>Procedure:-</u> </h6>
   ![image](https://user-images.githubusercontent.com/61331272/140736643-7187ef37-c9ed-460e-b64e-1584063f50fd.png)
<br/>
```
procedure getEmployeeById(
        id_in IN EMPLOYEE.ID%type,
        e_disp OUT SYS_REFCURSOR
    ) IS
        hasEmployee number;
    BEGIN
        hasEmployee := 0;
        SELECT count(*) into hasEmployee from EMPLOYEE where ID = id_in;
        IF hasEmployee <> 0 THEN --here <> means !=
            OPEN e_disp FOR SELECT * FROM EMPLOYEE WHERE ID = id_in;
        ELSE
            --return empty SYS_REFCURSOR couse 1=2 not equal always
            OPEN e_disp FOR SELECT * FROM EMPLOYEE WHERE 1=2;
        END IF;
    END getEmployeeById;
```
 <br/><h6><u>Call from java :-</u> </h6><br/>
 
 ![image](https://user-images.githubusercontent.com/61331272/140736794-a4776089-1bde-4e03-9040-44eed4bd454a.png)
 <br/>
 <b>3)Create a PL/SQL function (SUM of two number) and test it from SYSTEM<br/></b>
<b><u>Answer :- </u></b> 
<br/><h6><u>FUNCTION:-</u> </h6>
   ![image](https://user-images.githubusercontent.com/61331272/141075382-1e0ca616-fff1-42d8-8cdc-3f0c3310474d.png)<br/>
   ```
   create or replace function adder(n1 in number, n2 in number)
    return number
    is
    n3 number(8);
begin
    n3 :=n1+n2;
    return n3;
end;
/
   ```
 <br/><h6><u>TEST :-</u> </h6><br/>
 
 ![image](https://user-images.githubusercontent.com/61331272/141075551-f52a26b5-64a1-43b2-ac8d-b14134c6f3bf.png) <br/>
 
 ```
 select adder(12,8) as sum from DUAL;
 ```
 <br/>

 
 
<b>7)Call a procedure inside a <b>FUNCTION</b> for get employee by ID<br/></b>
<b><u>Answer :- </u></b> <br/><h6><u>Procedure:-</u> </h6><br/>

 ![image](https://user-images.githubusercontent.com/61331272/141611012-0817e928-308b-4914-8647-009d09eaccb3.png)
 <br/>
 ```
 create procedure getEmployeeAllInsideFunctionCall(
    empl_idd IN EMPLOYEE.ID%type,
    e_disp OUT SYS_REFCURSOR
)
    is
begin
    e_disp := getEmployee(empl_idd);
end;
/
 ```
  <br/>
 <br/><p><u>Function :-</u> </p><br/>  

![image](https://user-images.githubusercontent.com/61331272/141611059-ede80b61-25bb-424e-8450-58c94c5a33fa.png)

 <br/>  
 
 
 ```
 create function getEmployee(
    emp_id EMPLOYEE.ID%type
) return SYS_REFCURSOR
    is
    -- hasEmployee      number;
    expectedEmployee SYS_REFCURSOR;
begin
    --  select count(*) into hasEmployee from EMPLOYEE where ID = emp_id;

    open expectedEmployee for select * from EMPLOYEE where ID = emp_id;
    if SQL%FOUND then
        return expectedEmployee;
    end if;
    if SQL%NOTFOUND then
        open expectedEmployee for select * from EMPLOYEE where 4 = 6;
        return expectedEmployee;
    end if;
    return expectedEmployee;
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('EMPLOYEE find error');
end;
/
 ```
 
 <br/>

 
 
<b>8)Create a Sequence and use and use it as a  table id and and create a view 🎥 that will return a result by joining multiple table and call the view from a procedure and call it from your application  <br/></b>
<b><u>Answer :- </u></b> <br/><h6><u>All Query:-</u> </h6><br/>
   
 <br/>
 
 ```
  CREATE SEQUENCE IncrementId
    INCREMENT BY 10
    START WITH 10
    MINVALUE 10
    MAXVALUE 100
    CYCLE
    CACHE 2;


CREATE TABLE Persons
(
    id   int,
    lastName varchar(255),
    fastName varchar(255),
    area varchar(255),
    address varchar(255)
);

CREATE TABLE PersonsProfession
(
    PersonID   int,
    Profession varchar(255)
);

insert into PERSONS values (INCREMENTID.nextval, 'Kibria', 'Golam', 'Tejgaon', 'Dhaka');--[INCREMENTID.nextval] call the SEQUENCE here
INSERT INTO PersonsProfession values (40, 'Teacher');
INSERT INTO PersonsProfession values (50, 'Engineer');
INSERT INTO PersonsProfession values (60, 'Doctor');


create or REPLACE VIEW PersonDetails
AS
SELECT p.*, pd.*
from PERSONS p
         left join PersonsProfession pd on p.ID = pd.PERSONID
order by p.ID desc;


create or replace procedure getPersonByCallingView(
    id_in IN PERSONS.ID%type,
    e_disp OUT SYS_REFCURSOR
) IS
    hasPerson number;
BEGIN
    hasPerson := 0;
    SELECT count(*) into hasPerson from PERSONDETAILS pd where pd.ID = id_in;
    IF hasPerson <> 0 THEN --here <> means !=
        OPEN e_disp FOR SELECT * from PERSONDETAILS pd where pd.ID = id_in;
    ELSE
        --return empty SYS_REFCURSOR couse 1=2 not equal always
        OPEN e_disp FOR SELECT * FROM EMPLOYEE WHERE 1 = 2;
    END IF;
END getPersonByCallingView;
 ```
 
  <br/>

 
 
<b>9)Select column values based on condition and check if any value is null replace it as 'This is null' in Oracle <br/></b>
<b><u>Answer :- </u></b> <br/><h6><u>All Query:-</u> </h6><br/>
   ![image](https://user-images.githubusercontent.com/61331272/141830877-8d47cd68-1963-4f0c-99b3-961d613f5f0b.png)
 
 ```
  select CASE
           when e.ID = 7 then
               'This is 7'
           else 'This is not seven'
           end
           as conditionResult,--This is one kind of conditions
       decode(e.ID,22,222,55,555) as conditionalId,--This is another type of conditions(here logic is if id 22 then it shows 222 otherwise if id is 55 it will show 555 )
       e.id,
       NVL(NAME, 'Name is null') as nullCheckName,
       email
from EMPLOYEE e;
 ```


      

