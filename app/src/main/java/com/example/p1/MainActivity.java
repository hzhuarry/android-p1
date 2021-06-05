package com.example.p1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.accounts.AccountAuthenticatorResponse;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.Destroyable;

public class MainActivity extends AppCompatActivity implements CustomAdapter.OnCardListener {

    private static final String TAG = "MainActivity";
    private int listCounter = 0;

    private List<String> mCourseTitles;
    private List<String> courseTitles;

    private List<String> mCourseNames;
    private List<String> courseNames;

    private List<String> mCourseDescripts;
    private List<String> courseDescripts;

    private List<List<String>> mPreReqs;
    private List<List<String>> preReqs;

    private List<Integer> mCreditList;
    private List<Integer> creditList;

    private EditText searchInput;
    private RecyclerView recyclerView;
    private CustomAdapter customAdapter;
    private GridLayoutManager gridLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchInput = findViewById(R.id.search_bar);
        searchInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                courseTitles = new ArrayList<>();
                courseNames = new ArrayList<>();
                courseDescripts = new ArrayList<>();
                creditList = new ArrayList<>();
                preReqs = new ArrayList<>();

                String input = charSequence.toString().toLowerCase();
                for (int j = 0; j < mCourseTitles.size(); ++j) {
                    String item = mCourseTitles.get(j).toLowerCase();
                    if (item.contains(input)) {
                        courseTitles.add(mCourseTitles.get(j));
                        courseNames.add(mCourseNames.get(j));
                        courseDescripts.add(mCourseDescripts.get(j));
                        creditList.add(mCreditList.get(j));
                        preReqs.add(mPreReqs.get(j));
                    }
                }
                CustomAdapter newDisplay;
                if (input.length() != 0) {
                    newDisplay = new CustomAdapter(courseTitles, courseNames, MainActivity.this);
                }
                else {
                    newDisplay = new CustomAdapter(mCourseTitles, mCourseNames, MainActivity.this);
                }
                recyclerView.setAdapter(newDisplay);
            }

            @Override
            public void afterTextChanged(Editable editable) { }
        });


        //init master
        mCourseTitles = new ArrayList<>();
        mCourseNames = new ArrayList<>();
        mCourseDescripts = new ArrayList<>();
        mCreditList = new ArrayList<>();
        mPreReqs = new ArrayList<>();

        //init copy
        courseTitles = new ArrayList<>();
        courseNames = new ArrayList<>();
        courseDescripts = new ArrayList<>();
        creditList = new ArrayList<>();
        preReqs = new ArrayList<>();

        //insert elements to master
        mCourseTitles.add("CS1100");
        mCourseNames.add("Freshman Leap Seminar");
        mCourseDescripts.add("Small group discussions with " +
                "first year students are led by one or more faculty members and include a variety of foundational, " +
                "motivational, and topical subjects for computationalist.");
        mCreditList.add(1);
        mPreReqs.add(new ArrayList<String>());
        mPreReqs.get(listCounter).add("None");
        ++listCounter;

        mCourseTitles.add("CS1171");
        mCourseNames.add("Computing in MATLAB");
        mCourseDescripts.add("For students with a solid introductory computing background needing to demonstrate proficiency in the MATLAB language.");
        mCreditList.add(1);
        mPreReqs.add(new ArrayList<String>());
        mPreReqs.get(listCounter).add("(CS1301 or CS15XX or CS13X1 or CS1315)");
        ++listCounter;

        mCourseTitles.add("CS1301");
        mCourseNames.add("Introduction to Computing");
        mCourseDescripts.add("Introduction to computing principles and " +
                "programming practices with an emphasis on the design, " +
                "construction and implementation of problem solutions use of software tools.");
        mCreditList.add(3);
        mPreReqs.add(new ArrayList<String>());
        mPreReqs.get(listCounter).add("None");
        ++listCounter;

        mCourseTitles.add("CS1331");
        mCourseNames.add("Introduction to Object Oriented Programming");
        mCourseDescripts.add("Introduction to techniques and methods of object-oriented programming such an encapsulation, " +
                "inheritance, and polymorphism. " +
                "Emphasis on software development and individual programming skills.");
        mCreditList.add(3);
        mPreReqs.add(new ArrayList<String>());
        mPreReqs.get(listCounter).add("(CS1301 or CS1315 or CS1371)");
        ++listCounter;

        mCourseTitles.add("CS1332");
        mCourseNames.add("Datacourses Structures and Algorithms for Applications");
        mCourseDescripts.add("Computer data structures and algorithms in the context " +
                "of object-oriented programming. Focus on software development towards applications.");
        mCreditList.add(3);
        mPreReqs.add(new ArrayList<String>());
        mPreReqs.get(listCounter).add("CS1331");
        ++listCounter;

        mCourseTitles.add("CS2050");
        mCourseNames.add("Introduction to Discrete Mathematics for Computer Science");
        mCourseDescripts.add("Proof methods, strategy, correctness of algorithms over discrete structures. " +
                "Induction and recursion. Complexity and order of growth. Number theoretic principles and algorithms. Counting and computability.");
        mCreditList.add(3);
        mPreReqs.add(new ArrayList<String>());
        mPreReqs.get(listCounter).add("None");
        ++listCounter;

        mCourseTitles.add("CS2110");
        mCourseNames.add("Computer Organization and Programming");
        mCourseDescripts.add("An introduction to basic computer hardware, machine language, assembly language, and C programming.");
        mCreditList.add(4);
        mPreReqs.add(new ArrayList<String>());
        mPreReqs.get(listCounter).add("CS1332");
        ++listCounter;

        mCourseTitles.add("CS2200");
        mCourseNames.add("Systems and Networks");
        mCourseDescripts.add("A broad exposure to computer system structure and networking including software " +
                "abstractions in operating systems for orchestrating the usage of the computing resources.");
        mCreditList.add(4);
        mPreReqs.add(new ArrayList<String>());
        mPreReqs.get(listCounter).add("CS2110");
        ++listCounter;

        mCourseTitles.add("CS2340");
        mCourseNames.add("Objects and Design");
        mCourseDescripts.add("Object-oriented programming methods for dealing with large programs. " +
                "Focus on quality processes, effective debugging techniques, and testing to assure a quality product.");
        mCreditList.add(4);
        mPreReqs.add(new ArrayList<String>());
        mPreReqs.get(listCounter).add("(CS 1331 or 1372 or 1316)");
        ++listCounter;

        mCourseTitles.add("CS3210");
        mCourseNames.add("Design-Operating Systems");
        mCourseDescripts.add("Operating systems concepts, including multi-threading, scheduling, synchronization, " +
                "communication, and access control. Projects will cover design and implementation of several operating systems components.");
        mCreditList.add(3);
        mPreReqs.add(new ArrayList<String>());
        mPreReqs.get(listCounter).add("(CS2200 or ECE3057)");
        ++listCounter;

        mCourseTitles.add("CS3220");
        mCourseNames.add("Processor Design");
        mCourseDescripts.add("Principles in pipelined processor design, with emphasis on the need for a close interaction between code generation and architecture.");
        mCreditList.add(3);
        mPreReqs.add(new ArrayList<String>());
        mPreReqs.get(listCounter).add("(CS2200 or ECE3057)");
        mPreReqs.get(listCounter).add("ECE2031");
        ++listCounter;

        mCourseTitles.add("CS3240");
        mCourseNames.add("Languages and Computation");
        mCourseDescripts.add("Interpreters as abstract machines and the tools used to construct them, such as scanners and parsers. " +
                "An introduction to models of computation as embodied by different programming languages. Limits of and relationships between these models.");
        mCreditList.add(3);
        mPreReqs.add(new ArrayList<String>());
        mPreReqs.get(listCounter).add("CS2340");
        ++listCounter;

        mCourseTitles.add("CS3251");
        mCourseNames.add("Computer Networking I");
        mCourseDescripts.add("Introduction to problems in computer networking, including error recovery, medium access, routing, flow control, " +
                "and transport. Emphasis on current best practice. Includes programming of networked applications.");
        mCreditList.add(3);
        mPreReqs.add(new ArrayList<String>());
        mPreReqs.get(listCounter).add("CS2200");
        mPreReqs.get(listCounter).add("ECE3057");
        ++listCounter;

        mCourseTitles.add("CS3300");
        mCourseNames.add("Intro to Software Engr");
        mCourseDescripts.add("Team-based project class to introduce and apply software engineering principles and practices.");
        mCreditList.add(3);
        mPreReqs.add(new ArrayList<String>());
        mPreReqs.get(listCounter).add("CS2340");
        ++listCounter;

        mCourseTitles.add("CS3451");
        mCourseNames.add("Computer Graphics");
        mCourseDescripts.add("None");
        mCreditList.add(3);
        mPreReqs.add(new ArrayList<String>());
        mPreReqs.get(listCounter).add("(CS2110 or CS2261)");
        mPreReqs.get(listCounter).add("MATH2605");
        ++listCounter;

        mCourseTitles.add("CS3510");
        mCourseNames.add("Design and Analysis of Algorithms");
        mCourseDescripts.add("Basic techniques of design and analysis of efficient algorithms for standard " +
                "computational problems. NP-Completeness.");
        mCreditList.add(3);
        mPreReqs.add(new ArrayList<String>());
        mPreReqs.get(listCounter).add("(CS2050 or CS2051 or MATH2106)");
        mPreReqs.get(listCounter).add("(CS1332 or MATH3012 or MATH3022)");
        ++listCounter;

        mCourseTitles.add("CS3600");
        mCourseNames.add("Intro-Artificial Intell");
        mCourseDescripts.add("An introduction to artificial intelligence and machine learning. Topics include intelligent system " +
                "design methodologies, search and problem solving, supervised and reinforced learning.");
        mCreditList.add(3);
        mPreReqs.add(new ArrayList<String>());
        mPreReqs.get(listCounter).add("CS1332");
        ++listCounter;

        mCourseTitles.add("CS3630");
        mCourseNames.add("Intro-Perception & Robotic");
        mCourseDescripts.add("Covers fundamental problems and leading solutions for computer and robot perception and action from the point of view of autonomous robot navigation.");
        mCreditList.add(3);
        mPreReqs.add(new ArrayList<String>());
        mPreReqs.get(listCounter).add("CS1332");
        ++listCounter;

        mCourseTitles.add("CS3651");
        mCourseNames.add("Prototyping Intelligent Device");
        mCourseDescripts.add("Hands-on course teaching the fundamentals of electronics of electrical and mechanical prototyping.");
        mCreditList.add(4);
        mPreReqs.add(new ArrayList<String>());
        mPreReqs.get(listCounter).add("ECE2031");
        ++listCounter;

        mCourseTitles.add("CS3700");
        mCourseNames.add("Intro-Cognitive Science");
        mCourseDescripts.add("Multidisciplinary perspectives on cognitive science. Interdisciplinary approaches to issues in cognition, " +
                "including memory, language, problem solving, learning, perception, and action.");
        mCreditList.add(3);
        mPreReqs.add(new ArrayList<String>());
        mPreReqs.get(listCounter).add("None");
        ++listCounter;

        mCourseTitles.add("CS4210");
        mCourseNames.add("Adv Operating Systems");
        mCourseDescripts.add("Operating system abstractions and their implementations, multi-threading, efficient inter-address communication, " +
                "high-level synchronization, introduction to multi-processor and distributed operating systems, real-time systems.");
        mCreditList.add(3);
        mPreReqs.add(new ArrayList<String>());
        mPreReqs.get(listCounter).add("CS3210");
        ++listCounter;

        mCourseTitles.add("CS4220");
        mCourseNames.add("Embedded Systems");
        mCourseDescripts.add("Design principles, programming techniques, and case studies of embedded real-time systems. " +
                "Interface techniques and devices. Representations and reasoning about physical processes.");
        mCreditList.add(3);
        mPreReqs.add(new ArrayList<String>());
        mPreReqs.get(listCounter).add("(CS2200 or ECE3057)");
        ++listCounter;

        mCourseTitles.add("CS4235");
        mCourseNames.add("Intro to Info Security");
        mCourseDescripts.add("Terms/concepts, threats, controls; problem definition; comprehensive information security model; security for " +
                "operating systems, databases, network/distributed systems; administering security; legal/ethical/policy issues.");
        mCreditList.add(3);
        mPreReqs.add(new ArrayList<String>());
        mPreReqs.get(listCounter).add("(CS2200 or ECE3057)");
        ++listCounter;

        mCourseTitles.add("CS4237");
        mCourseNames.add("Comp & Network Security");
        mCourseDescripts.add("Fundamental concepts and principles of computer security, operating system and database security, " +
                "secret key and public key cryptographic algorithms, hash functions, authentication, firewalls and intrusion detection systems, IPSec ad VPN, and wireless security.");
        mCreditList.add(3);
        mPreReqs.add(new ArrayList<String>());
        mPreReqs.get(listCounter).add("CS3251");
        ++listCounter;

        mCourseTitles.add("CS4240");
        mCourseNames.add("Compilers & Interpreters");
        mCourseDescripts.add("Study of techniques for the design and implementation of compilers, interpreters, and program analyzers, " +
                "with consideration of the particular characteristics of widely used programming languages.");
        mCreditList.add(3);
        mPreReqs.add(new ArrayList<String>());
        mPreReqs.get(listCounter).add("CS2340");

        mCourseTitles.add("CS4251");
        mCourseNames.add("Computer Networking II");
        mCourseDescripts.add("Principles of computer networks, including medium access, ARQ protocols, routing, congestion " +
                "avoidance, and control. Emphasis on design options and tradeoffs. Includes significant network application programming.");
        mCreditList.add(3);
        mPreReqs.add(new ArrayList<String>());
        mPreReqs.get(listCounter).add("CS3251");
        ++listCounter;

        mCourseTitles.add("CS4255");
        mCourseNames.add("Intro-Network Management");
        mCourseDescripts.add("Introduction to SNMP-based network management. Practical application to network and system management including hands-on lab practice.");
        mCreditList.add(3);
        mPreReqs.add(new ArrayList<String>());
        mPreReqs.get(listCounter).add("CS3251");
        ++listCounter;

        mCourseTitles.add("CS4261");
        mCourseNames.add("Mobile Apps & Svcs");
        mCourseDescripts.add("This course provides an introduction to mobile applications and services with an emphasis on voice and data service integration in modern commercial networks.");
        mCreditList.add(3);
        mPreReqs.add(new ArrayList<String>());
        mPreReqs.get(listCounter).add("CS2200");
        mPreReqs.get(listCounter).add("ECE3057");
        ++listCounter;

        mCourseTitles.add("CS4270");
        mCourseNames.add("Data Communications Lab");
        mCourseDescripts.add("Detailed study of the principles of data transmission systems and their performance, reinforced by laboratory exercises.");
        mCreditList.add(3);
        mPreReqs.add(new ArrayList<String>());
        mPreReqs.get(listCounter).add("CS3251");
        ++listCounter;

        mCourseTitles.add("CS4290");
        mCourseNames.add("Advanced Computer Org");
        mCourseDescripts.add("Topics concerning the hardware design of computer systems. Advanced techniques in high-performance pipelined central processing units. " +
                "Memory and I/O systems. Parallel processors including shared-memory multiprocessors and cluster computers.");
        mCreditList.add(3);
        mPreReqs.add(new ArrayList<String>());
        mPreReqs.get(listCounter).add("(CS2200 or ECE3057)");
        ++listCounter;

        mCourseTitles.add("CS4365");
        mCourseNames.add("Intro Enterprise Comp");
        mCourseDescripts.add("A survey of basic software tools and techniques used in mission-critical systems and applications, combined with in-depth " +
                "study of fundamental principles underlying enterprise computing.");
        mCreditList.add(3);
        mPreReqs.add(new ArrayList<String>());
        mPreReqs.get(listCounter).add("(CS3210 or CS 4400)");
        ++listCounter;

        mCourseTitles.add("CS4400");
        mCourseNames.add("Intro to Database Systems");
        mCourseDescripts.add("Comprehensive coverage of mainstream database concepts such as the entity-relationship model, relational databases, " +
                "query languages, and database design methodology. Includes a project.");
        mCreditList.add(3);
        mPreReqs.add(new ArrayList<String>());
        mPreReqs.get(listCounter).add("(CS1301 or CS1315 or CS1371)");
        ++listCounter;

        mCourseTitles.add("CS4420");
        mCourseNames.add("Database Sys Implement");
        mCourseDescripts.add("Study of fundamental software components/algorithms of a database system, including the file manager, " +
                "query engine, lock manager, and recovery manager. Includes a project component.");
        mCreditList.add(3);
        mPreReqs.add(new ArrayList<String>());
        mPreReqs.get(listCounter).add("CS4400");
        ++listCounter;

        mCourseTitles.add("CS4440");
        mCourseNames.add("Database Technologies");
        mCourseDescripts.add("The course will cover current developments including distributed, object-oriented, temporal-spatial, " +
                "Web-based, mobile, and active database technologies, and data warehousing and mining applications.");
        mCreditList.add(3);
        mPreReqs.add(new ArrayList<String>());
        mPreReqs.get(listCounter).add("CS4400");
        ++listCounter;

        mCourseTitles.add("CS4455");
        mCourseNames.add("Video Game Design");
        mCourseDescripts.add("Techniques for electronic game design and programming, including graphics game engines, motion generation, behavioral control for autonomous characters, " +
                "interaction structure, social and interface issues of multi-user play, and the business aspects of game development.");
        mCreditList.add(3);
        mPreReqs.add(new ArrayList<String>());
        mPreReqs.get(listCounter).add("CS3451");
        ++listCounter;

        mCourseTitles.add("CS4460");
        mCourseNames.add("Intro Info Visualization");
        mCourseDescripts.add("Introduction to principles and techniques of infomation visualization, the presentation of " +
                "primarily abstract data to help people understand, analyze and make sense of data. ");
        mCreditList.add(3);
        mPreReqs.add(new ArrayList<String>());
        mPreReqs.get(listCounter).add("CS1332");
        ++listCounter;

        mCourseTitles.add("CS4464");
        mCourseNames.add("Computational Journalism Print");
        mCourseDescripts.add("A study of computational and technological advancements in journalism with emphasis on technologies for " +
                "developing new tools and their potential impact on news and information.");
        mCreditList.add(3);
        mPreReqs.add(new ArrayList<String>());
        mPreReqs.get(listCounter).add("CS1331");
        ++listCounter;

        mCourseTitles.add("CS4470");
        mCourseNames.add("User Interface Software");
        mCourseDescripts.add("Concepts, techniques, structures, and strategies for implementation of interactive software.");
        mCreditList.add(3);
        mPreReqs.add(new ArrayList<String>());
        mPreReqs.get(listCounter).add("CS2340");
        mPreReqs.get(listCounter).add("(CS3750 or PSYC3750)");
        ++listCounter;

        mCourseTitles.add("CS4472");
        mCourseNames.add("Design of Online Comm");
        mCourseDescripts.add("Introduction to the design of online communities. Students study an existing community in depth.");
        mCreditList.add(3);
        mPreReqs.add(new ArrayList<String>());
        mPreReqs.get(listCounter).add("None");
        ++listCounter;

        mCourseTitles.add("CS4475");
        mCourseNames.add("Comp Photography");
        mCourseDescripts.add("An introductory course on the scientific, technical, perceptual, and aesthetic principles of pictures. Emphasis is on the techniques of image formation," +
                " analysis, merging, modification and their use for depiction of reality on a 2D medium of photographs.");
        mCreditList.add(3);
        mPreReqs.add(new ArrayList<String>());
        mPreReqs.get(listCounter).add("(CS1301 or CS1315 or CS1371)");
        ++listCounter;

        mCourseTitles.add("CS4476");
        mCourseNames.add("Intro to Computer Vision");
        mCourseDescripts.add("Introduction to computer vision including fundamentals of image formation, camera imaging geometry, " +
                "feature detection and matching, stereo, motion estimation and tracking, image classification and scene understanding.");
        mCreditList.add(3);
        mPreReqs.add(new ArrayList<String>());
        mPreReqs.get(listCounter).add("(MATH2605 or MATH2401 or MATH24X1 or MATH2411 or MATH1553 or MATH1X54 or MATH1554 or MATH1564 or CS2110 or CS2261" +
                " or (ECE2020 and ECE2035)");
        ++listCounter;

        mCourseTitles.add("CS4480");
        mCourseNames.add("Digital Video Special FX");
        mCourseDescripts.add("A study of digital multimedia and the analysis and synthesis of digital video. Special attention paid to techniques for generating video special effects.");
        mCreditList.add(3);
        mPreReqs.add(new ArrayList<String>());
        mPreReqs.get(listCounter).add("CS3451");
        ++listCounter;

        mCourseTitles.add("CS4496");
        mCourseNames.add("Computer Animation");
        mCourseDescripts.add("Motion techniques for computer animation and interactive games (keyframing, procedural methods, motion capture, " +
                "and simulation) and principles for storytelling, composition, lighting, and interactivity.");
        mCreditList.add(3);
        mPreReqs.add(new ArrayList<String>());
        mPreReqs.get(listCounter).add("CS3451");
        ++listCounter;

        mCourseTitles.add("CS4510");
        mCourseNames.add("Automata and Complexity");
        mCourseDescripts.add("Computational machine models and their language classes. Undecidability. Resource-bounded computations. " +
                "Central complexity-theoretic concepts such as complexity classes, reducibility and completeness.");
        mCreditList.add(3);
        mPreReqs.add(new ArrayList<String>());
        mPreReqs.get(listCounter).add("(CS3510 or CS3511)");
        mPreReqs.get(listCounter).add("(MATH3012 or MATH3022)");
        mPreReqs.get(listCounter).add("(MATH3215 or MATH3235 or MATH3225 or MATH3770 or MATH3670 or CEE3770 or ISYE3770 or (ISYE2027 and ISYE2028)");
        ++listCounter;

        mCourseTitles.add("CS4540");
        mCourseNames.add("Advanced Algs");
        mCourseDescripts.add("Advanced techniques for designing and analyzing efficient algorithms for combinatorial, algebraic, and number theoretic problems");
        mCreditList.add(3);
        mPreReqs.add(new ArrayList<String>());
        mPreReqs.get(listCounter).add("(CS3510 or CS3511)");
        ++listCounter;

        mCourseTitles.add("CS4590");
        mCourseNames.add("Computer Audio");
        mCourseDescripts.add("A well-rounded exploration of digital audio and its importance in current research and applications. " +
                "Exposes students to the principles, technology, and current research of computer audio.");
        mCreditList.add(3);
        mPreReqs.add(new ArrayList<String>());
        mPreReqs.get(listCounter).add("None");
        ++listCounter;

        mCourseTitles.add("CS4605");
        mCourseNames.add("Mobile & Ubiquitous Comp");
        mCourseDescripts.add("Investigates the infrastructure required to develop mobile and ubiquitous computing applications and establishes major research themes and experimental practices.");
        mCreditList.add(3);
        mPreReqs.add(new ArrayList<String>());
        mPreReqs.get(listCounter).add("CS2110 or CS2261 or (ECE2020 and ECE2035)");
        ++listCounter;

        mCourseTitles.add("CS4625");
        mCourseNames.add("Internet Sys & Services");
        mCourseDescripts.add("Focusing on fundamental issues, concepts, techniques, and technical challenges that are critical for designing and developing " +
                "Internet systems, services and applications.");
        mCreditList.add(3);
        mPreReqs.add(new ArrayList<String>());
        mPreReqs.get(listCounter).add("(CS2200 or ECE3057)");
        ++listCounter;

        mCourseTitles.add("CS4635");
        mCourseNames.add("Knowledge-Based AI");
        mCourseDescripts.add("Structured knowledge representation; knowledge-based methods of reasoning and learning; problem-solving, modeling and design.");
        mCreditList.add(3);
        mPreReqs.add(new ArrayList<String>());
        mPreReqs.get(listCounter).add("CS3600");
        ++listCounter;

        mCourseTitles.add("CS4641");
        mCourseNames.add("Machine Learning");
        mCourseDescripts.add("Machine learning techniques and applications. Topics include foundational issues; inductive, " +
                "analytical, numerical, and theoretical approaches; and real-world applications.");
        mCreditList.add(3);
        mPreReqs.add(new ArrayList<String>());
        mPreReqs.get(listCounter).add("CS1331");
        ++listCounter;

        mCourseTitles.add("CS4646");
        mCourseNames.add("Mach Learn for Trading");
        mCourseDescripts.add("This course introduces students to the real world challenges of implementing machine learning based " +
                "strategies including the algorithmic steps from information gathering to market orders.");
        mCreditList.add(3);
        mPreReqs.add(new ArrayList<String>());
        mPreReqs.get(listCounter).add("CS3600");
        ++listCounter;

        mCourseTitles.add("CS4649");
        mCourseNames.add("Robot Intelli Planning");
        mCourseDescripts.add("We investigate algorithms for robots and complex systems that make intelligent decisions. Emphasis on the " +
                "theoretical and empirical properties of classical, geometric, stochastic/dynamic planning.");
        mCreditList.add(3);
        mPreReqs.add(new ArrayList<String>());
        mPreReqs.get(listCounter).add("CS1332");
        ++listCounter;

        mCourseTitles.add("CS4650");
        mCourseNames.add("Natural Language");
        mCourseDescripts.add("Methodologies for designing systems that comprehend natural language. Topics include lexical analysis, parsing, " +
                "interpretation of sentences, semantic representation, organization of knowledge, and inference mechanisms.");
        mCreditList.add(3);
        mPreReqs.add(new ArrayList<String>());
        mPreReqs.get(listCounter).add("(CS 3510 or CS3511)");
        ++listCounter;

        mCourseTitles.add("CS4660");
        mCourseNames.add("Educational Technology");
        mCourseDescripts.add("Introduction to the theory and practice of educational technology. Covers learning theory applicable to " +
                "educational technology, explains major research findings. ");
        mCreditList.add(3);
        mPreReqs.add(new ArrayList<String>());
        mPreReqs.get(listCounter).add("None");
        ++listCounter;

        mCourseTitles.add("CS4731");
        mCourseNames.add("Game AI");
        mCourseDescripts.add("Examines the expressive possibilities of artificial intelligence techniques in computer games. " +
                "Students learn AI programming techniques, and how they strongly interface with game design.");
        mCreditList.add(3);
        mPreReqs.add(new ArrayList<String>());
        mPreReqs.get(listCounter).add("CS3600");
        ++listCounter;

        mCourseTitles.add("CS4745");
        mCourseNames.add("Info & Com Tech & Global Dev");
        mCourseDescripts.add("Focus on technology design, adoption, and use as seen through the lens of global development.");
        mCreditList.add(3);
        mPreReqs.add(new ArrayList<String>());
        mPreReqs.get(listCounter).add("None");
        ++listCounter;


        //transfer to copy
        courseTitles.addAll(mCourseTitles);
        courseNames.addAll(mCourseNames);
        courseDescripts.addAll(mCourseDescripts);
        creditList.addAll(mCreditList);
        preReqs.addAll(mPreReqs);


        recyclerView = findViewById(R.id.recycler_view);

        customAdapter = new CustomAdapter(courseTitles, courseNames, this);
        recyclerView.setAdapter(customAdapter);

        gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);
    }

    @Override
    public void onCardClick(int position) {
//        Log.d(TAG, "Position: " + position + ", Course Name : " + courseTitles.get(position) + ", Course Description: " + courseNames.get(position));
        Bundle bundle = new Bundle();
        bundle.putString("course_title", courseTitles.get(position));
        bundle.putString("course_name", courseNames.get(position));
        bundle.putString("course_descript", courseDescripts.get(position));
        bundle.putInt("credit", creditList.get(position));
        bundle.putStringArrayList("prereq_list", (ArrayList<String>) preReqs.get(position));


        Intent intent = new Intent(this, second_activity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

}