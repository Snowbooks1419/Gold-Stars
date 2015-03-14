package goldstars;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.*;

public class GoldStars extends JFrame implements ActionListener, ItemListener {
    //Gold Stars Variables
    static final int buildNumber = 12125;
    static final String name = "Gold Stars 20 Beta 1";
    static final String version = "Version 20.0";
    static boolean newUser = true;
    static Color color1;
    static Color color2;
    static File goldStarsFolder = new File(System.getProperty("user.home"), "Gold Stars");
    static String color;
    static String oldBuild;
    static String output;
    static String theme;
    static String username;

    //Calendar Variables
    static String calendarFormat;
    static ArrayList<String> calendarEvents = new ArrayList<String>();

    //Calculator Variables
    static String multiplicationSymbol;
    static String divideSymbol;

    //Notes Variables
    static ArrayList<String> notes = new ArrayList<String>();
    static ArrayList<String> noteNames = new ArrayList<String>();
    static boolean notesSearchCaseSensitive;
    static int notesTemp;
    static boolean passwordProtectedNotes = false;

    //Photos Variables
    static boolean photosSearchCaseSensitive;
    static boolean passwordProtectedPhotos = false;
    static ArrayList<String> photoNames = new ArrayList<String>();

    //Gold Stars Talk Variables
    static boolean motherNameInputted = false;
    static boolean fatherNameInputted = false;
    static boolean homework = false;
    static boolean homework2 = false;
    static boolean howAreYou = false;
    static boolean likeIceCream = false;
    static boolean iceCreamFlavor = false;
    static boolean likeCookies = false;
    static boolean typeOfCookies = false;
    static String nickname;
    static String occupation;
    static String location;

    //Gold Center Variables
    static ArrayList<String> games = new ArrayList<String>();
    static ArrayList<String> apps = new ArrayList<String>();

    //Settings Variables
    static boolean passwordYes = false;
    static String password = new String();
    static String language;

    //Error Log Variables
    static ArrayList<String> errors = new ArrayList<String>();
    static boolean errorLogSearchCaseSensitive;

    //Search Variables
    static boolean searchCaseSensitive;
    static JTextField searchTextField = new JTextField(20);

    public static void main(String[] args) {

        // Performance monitor
        Thread performanceMonitor = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    double maxMemory = Runtime.getRuntime().maxMemory();
                    double danger = maxMemory * 0.8;
                    double usedMemory = maxMemory - Runtime.getRuntime().freeMemory();


                    if (usedMemory >= danger) {
                        System.out.println("Dangerous memory usage: " + NumberFormat.getIntegerInstance().format(new BigDecimal(usedMemory)) + " of " + NumberFormat.getIntegerInstance().format(new BigDecimal(maxMemory)) + " (" + NumberFormat.getPercentInstance().format(new BigDecimal(usedMemory / maxMemory)) + ")");
                    }

                }
            }
        });
        performanceMonitor.start();

        downloadCalendarEvents();
        downloadNotes();
        downloadNoteNames();
        downloadPhotos();
        downloadGames();
        downloadApps();
        if (!goldStarsFolder.exists()) {
            goldStarsFolder.mkdir();
        }
        File calendarFormatTemp = new File(goldStarsFolder, "calendarFormat.txt");
        try {
            calendarFormat = new Scanner(calendarFormatTemp).useDelimiter("\\Z").next();
        } catch (FileNotFoundException e) {
            calendarFormat = "Format 1";
            File file = new File(goldStarsFolder, "calendarFormat.txt");
            PrintWriter out;
            try {
                out = new PrintWriter(file);
                out.println(calendarFormat);
                out.close();
            } catch (FileNotFoundException e2) {

            }
        }
        File multiplicationSymbolTemp = new File(goldStarsFolder, "multiplicationSymbol.txt");
        try {
            multiplicationSymbol = new Scanner(multiplicationSymbolTemp).useDelimiter("\\Z").next();
        } catch (FileNotFoundException e) {
            File file = new File(goldStarsFolder, "multiplicationSymbol.txt");
            multiplicationSymbol = "×";
            PrintWriter out;
            try {
                out = new PrintWriter(file);
                out.println(multiplicationSymbol);
                out.close();
            } catch (FileNotFoundException e2) {

            }
        }
        File divisionSymbolTemp = new File(goldStarsFolder, "divideSymbol.txt");
        try {
            divideSymbol = new Scanner(divisionSymbolTemp).useDelimiter("\\Z").next();
        } catch (FileNotFoundException e) {
            File file = new File(goldStarsFolder, "dividesymbol.txt");
            divideSymbol = "÷";
            PrintWriter out;
            try {
                out = new PrintWriter(file);
                out.println(divideSymbol);
                out.close();
            } catch (FileNotFoundException e2) {

            }
        }
        File languageTemp = new File(goldStarsFolder, "language.txt");
        try {
            language = new Scanner(languageTemp).useDelimiter("\\Z").next();
            newUser = false;
        } catch (FileNotFoundException e) {
            File file = new File(goldStarsFolder, "language.txt");
            language = "1";
            PrintWriter out;
            try {
                out = new PrintWriter(file);
                out.println(language);
                out.close();
            } catch (FileNotFoundException e2) {

            }
        }
        try {
            String temp = new Scanner(new File("passwordProtectedNotes.txt")).useDelimiter("\\Z").next();
            if ("1".equals(temp)) {
                passwordProtectedNotes = true;
            } else {
                passwordProtectedNotes = false;
            }
        } catch (FileNotFoundException e) {
            passwordProtectedNotes = false;
            PrintWriter out;
            try {
                out = new PrintWriter("passwordProtectedNotes.txt");
                out.println("0");
                out.close();
            } catch (FileNotFoundException e2) {

            }
        }
        try {
            String temp = new Scanner(new File("notesSearchCaseSensitive.txt")).useDelimiter("\\Z").next();
            if ("1".equals(temp)) {
                notesSearchCaseSensitive = true;
            } else {
                notesSearchCaseSensitive = false;
            }
        } catch (FileNotFoundException e) {
            notesSearchCaseSensitive = false;
            PrintWriter out;
            try {
                out = new PrintWriter("notesSearchCaseSensitive.txt");
                out.println("0");
                out.close();
            } catch (FileNotFoundException e2) {

            }
        }
        try {
            String temp = new Scanner(new File("passwordProtectedPhotos.txt")).useDelimiter("\\Z").next();
            if ("1".equals(temp)) {
                passwordProtectedPhotos = true;
            } else {
                passwordProtectedPhotos = false;
            }
        } catch (FileNotFoundException e) {
            passwordProtectedPhotos = false;
            PrintWriter out;
            try {
                out = new PrintWriter("passwordProtectedPhotos.txt");
                out.println("0");
                out.close();
            } catch (FileNotFoundException e2) {

            }
        }
        try {
            String temp = new Scanner(new File("photosSearchCaseSensitive.txt")).useDelimiter("\\Z").next();
            if ("1".equals(temp)) {
                photosSearchCaseSensitive = true;
            } else {
                photosSearchCaseSensitive = false;
            }
        } catch (FileNotFoundException e) {
            photosSearchCaseSensitive = false;
            PrintWriter out;
            try {
                out = new PrintWriter("photosSearchCaseSensitive.txt");
                out.println("0");
                out.close();
            } catch (FileNotFoundException e2) {

            }
        }
        try {
            String temp = new Scanner(new File("searchCaseSensitive.txt")).useDelimiter("\\Z").next();
            if ("1".equals(temp)) {
                searchCaseSensitive = true;
            } else {
                searchCaseSensitive = false;
            }
        } catch (FileNotFoundException e) {
            searchCaseSensitive = false;
            PrintWriter out;
            try {
                out = new PrintWriter("searchCaseSensitive.txt");
                out.println("0");
                out.close();
            } catch (FileNotFoundException e2) {

            }
        }
        try {
            String temp = new Scanner(new File("errorLogSearchCaseSensitive.txt")).useDelimiter("\\Z").next();
            if ("1".equals(temp)) {
                errorLogSearchCaseSensitive = true;
            } else {
                errorLogSearchCaseSensitive = false;
            }
        } catch (FileNotFoundException e) {
            errorLogSearchCaseSensitive = false;
            PrintWriter out;
            try {
                out = new PrintWriter("errorLogSearchCaseSensitive.txt");
                out.println("0");
                out.close();
            } catch (FileNotFoundException e2) {

            }
        }
        try {
            oldBuild = new Scanner(new File("oldBuild.txt")).useDelimiter("\\Z").next();
            int oldBuildNumber = Integer.parseInt(oldBuild);
            if (oldBuildNumber != buildNumber) {
                output = "Hi, and welcome to Gold Stars!\n"
                        + name + " includes many new features.\n"
                        + "Click OK to use the new Gold Stars!";
                JOptionPane.showMessageDialog(null, output, "Welcome to Gold Stars!", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (FileNotFoundException e) {
            output = "Hi, and welcome to Gold Stars!\n"
                    + "Gold Stars includes many exciting and useful features.\n"
                    + "However, before you can use it for the first time, we need to collect some information.\n"
                    + "Click OK to begin!";
            JOptionPane.showMessageDialog(null, output, "Welcome to Gold Stars!", JOptionPane.INFORMATION_MESSAGE);
            username = JOptionPane.showInputDialog(null, "STEP 1 OF 2\n"
                    + "Please enter your username:", "Setting up Gold Stars", JOptionPane.QUESTION_MESSAGE);
            while ("".equals(username)) {
                username = JOptionPane.showInputDialog(null, "ERROR:\n"
                        + "No Username Entered", "Setting up Gold Stars", JOptionPane.QUESTION_MESSAGE);
                errors.add("Error: No Username Entered");
            }
            if (username == null) {
                System.exit(0);
            }
            nickname = username;
            PrintWriter out;
            try {
                out = new PrintWriter("username.txt");
                out.println(username);
                out.close();
            } catch (FileNotFoundException e2) {

            }
            output = "STEP 2 OF 2\n"
                    + "Please enter your password:";
            password = JOptionPane.showInputDialog(null, output, "Setting up Gold Stars", JOptionPane.QUESTION_MESSAGE);
            if (password == null) {
                System.exit(0);
            }
            try {
                out = new PrintWriter("password.txt");
                out.println(password);
                out.close();
            } catch (FileNotFoundException e2) {

            }
        }
        PrintWriter out2;
        try {
            out2 = new PrintWriter("oldBuild.txt");
            out2.println(buildNumber);
            out2.close();
        } catch (FileNotFoundException e2) {

        }
        try {
            username = new Scanner(new File("username.txt")).useDelimiter("\\Z").next();
        } catch (FileNotFoundException e) {

        }
        try {
            nickname = new Scanner(new File("nickname.txt")).useDelimiter("\\Z").next();
        } catch (FileNotFoundException e) {
            nickname = username;
            PrintWriter out;
            try {
                out = new PrintWriter("nickname.txt");
                out.println(nickname);
                out.close();
            } catch (FileNotFoundException e2) {

            }
        }
        try {
            password = new Scanner(new File("password.txt")).useDelimiter("\\Z").next();
        } catch (FileNotFoundException e) {

        }
        try {
            theme = new Scanner(new File("theme.txt")).useDelimiter("\\Z").next();
        } catch (FileNotFoundException e) {
            theme = "Gold Stars";
            PrintWriter out;
            try {
                out = new PrintWriter("theme.txt");
                out.println(theme);
                out.close();
            } catch (FileNotFoundException e2) {

            }
        }
        try {
            color = new Scanner(new File("color.txt")).useDelimiter("\\Z").next();
        } catch (FileNotFoundException e) {

        }
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                dashboard();
            }
        });
    }

    public static JPanel createContentPane() {
        JPanel p = new JPanel();
        p.setOpaque(true);
        return p;
    }

    public static void dashboard() {
        JFrame frame = new JFrame("Gold Stars");
        GoldStars gs = new GoldStars();
        frame.setContentPane(gs.createContentPane());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1100, 575);
        frame.setExtendedState(frame.MAXIMIZED_BOTH);
        frame.setVisible(true);
        frame.setLayout(new BorderLayout());
        if ("Solid Color".equals(theme)) {
            if ("Black".equals(color)) {
                color1 = Color.black;
                color2 = Color.white;
            }
            if ("Blue".equals(color)) {
                color1 = Color.blue;
                color2 = Color.black;
            }
            if ("Cyan".equals(color)) {
                color1 = Color.cyan;
                color2 = Color.black;
            }
            if ("Dark Gray".equals(color)) {
                color1 = Color.darkGray;
                color2 = Color.white;
            }
            if ("Dark Green".equals(color)) {
                color1 = new Color(0x00, 0xC0, 0x00);
                color2 = Color.black;
            }
            if ("Green".equals(color)) {
                color1 = Color.green;
                color2 = Color.black;
            }
            if ("Light Red".equals(color)) {
                color1 = new Color(0xFF, 0x40, 0x40);
                color2 = Color.black;
            }
            if ("Magenta".equals(color)) {
                color1 = Color.magenta;
                color2 = Color.black;
            }
            if ("Orange".equals(color)) {
                color1 = Color.orange;
                color2 = Color.black;
            }
            if ("Pink".equals(color)) {
                color1 = Color.pink;
                color2 = Color.black;
            }
            if ("Red".equals(color)) {
                color1 = Color.red;
                color2 = Color.black;
            }
            if ("Very Dark Green".equals(color)) {
                color1 = new Color(0x00, 0x80, 0x00);
                color2 = Color.white;
            }
            if ("White".equals(color)) {
                color1 = Color.white;
                color2 = Color.black;
            }
            if ("Yellow".equals(color)) {
                color1 = Color.yellow;
                color2 = Color.black;
            }
            frame.getContentPane().setBackground(color1);
        }
        if ("Gold Stars".equals(theme)) {
            color1 = Color.yellow;
            color2 = Color.black;
        }
        if ("Forest".equals(theme)) {
            color1 = new Color(0x00, 0x80, 0x00);
            color2 = Color.white;
        }
        if ("Ocean".equals(theme)) {
            color1 = Color.blue;
            color2 = Color.white;
        }
        if ("Sky".equals(theme)) {
            color1 = Color.white;
            color2 = Color.blue;
        }
        if ("Snow".equals(theme)) {
            color1 = Color.white;
            color2 = Color.gray;
        }
        JTabbedPane tabbedPane = new JTabbedPane();
        JPanel homePanel = new JPanel();
        homePanel.setLayout(new GridLayout(3, 3));
        homePanel.setBackground(color1);
        JLabel emptySpaceA = new JLabel();
        homePanel.add(emptySpaceA);
        JLabel welcomeLabel = new JLabel("Welcome to Gold Stars!", SwingConstants.CENTER);
        welcomeLabel.setForeground(color2);
        welcomeLabel.setFont(new java.awt.Font(null, Font.BOLD, 22));
        homePanel.add(welcomeLabel);
        JLabel emptySpaceB = new JLabel();
        homePanel.add(emptySpaceB);
        JLabel goldStarsTalkLabel = new JLabel("Gold Stars Talk", SwingConstants.CENTER);
        goldStarsTalkLabel.setForeground(color2);
        goldStarsTalkLabel.setFont(new java.awt.Font(null, Font.BOLD, 16));
        goldStarsTalkLabel.addMouseListener(new GoldStarsTalkListener());
        homePanel.add(goldStarsTalkLabel);
        JLabel goldStarsStoriesLabel = new JLabel("Gold Stars Stories", SwingConstants.CENTER);
        goldStarsStoriesLabel.setForeground(color2);
        goldStarsStoriesLabel.setFont(new java.awt.Font(null, Font.BOLD, 16));
        goldStarsStoriesLabel.addMouseListener(new GoldStarsStoriesListener());
        homePanel.add(goldStarsStoriesLabel);
        JLabel filesLabel = new JLabel("Files", SwingConstants.CENTER);
        filesLabel.setForeground(color2);
        filesLabel.setFont(new java.awt.Font(null, Font.BOLD, 16));
        filesLabel.addMouseListener(new FilesListener());
        homePanel.add(filesLabel);
        JLabel notificationsLabel = new JLabel("Notifications", SwingConstants.CENTER);
        notificationsLabel.setForeground(color2);
        notificationsLabel.setFont(new java.awt.Font(null, Font.BOLD, 16));
        notificationsLabel.addMouseListener(new NotificationsListener());
        homePanel.add(notificationsLabel);
        JLabel searchLabel = new JLabel("Search", SwingConstants.CENTER);
        searchLabel.setForeground(color2);
        searchLabel.setFont(new java.awt.Font(null, Font.BOLD, 16));
        searchLabel.addMouseListener(new SearchListener());
        homePanel.add(searchLabel);
        JLabel helpLabel = new JLabel("Help", SwingConstants.CENTER);
        helpLabel.setForeground(color2);
        helpLabel.setFont(new java.awt.Font(null, Font.BOLD, 16));
        helpLabel.addMouseListener(new HelpListener());
        homePanel.add(helpLabel);
        tabbedPane.addTab("Home", homePanel);
        JPanel calendarPanel = new JPanel();
        calendarPanel.setLayout(new GridLayout(2, 3));
        calendarPanel.setBackground(color1);
        Calendar calendar = Calendar.getInstance();
        String[] days = new String[]
                {
                        "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"
                };
        JLabel todayIsLabel = new JLabel("Today is:", SwingConstants.CENTER);
        todayIsLabel.setOpaque(true);
        todayIsLabel.setBackground(color2);
        todayIsLabel.setForeground(color1);
        todayIsLabel.setFont(new java.awt.Font(null, Font.BOLD, 16));
        calendarPanel.add(todayIsLabel);
        switch (calendarFormat) {
            case "Format 1":
            default: {
                output = "Date: " + ((calendar.get(Calendar.MONTH) + 1) + "/") + (calendar.get(Calendar.DATE)) + "/" + (calendar.get(Calendar.YEAR));
                JLabel dateLabel = new JLabel(output, SwingConstants.CENTER);
                dateLabel.setForeground(color2);
                dateLabel.setFont(new java.awt.Font(null, Font.BOLD, 16));
                calendarPanel.add(dateLabel);
                output = "Day: " + days[calendar.get(Calendar.DAY_OF_WEEK) - 1] + "\n";
                JLabel dayLabel = new JLabel(output, SwingConstants.CENTER);
                dayLabel.setForeground(color2);
                dayLabel.setFont(new java.awt.Font(null, Font.BOLD, 16));
                calendarPanel.add(dayLabel);
                break;
            }
            case "Format 2": {
                output = "Date: " + (calendar.get(Calendar.DATE)) + "/" + ((calendar.get(Calendar.MONTH) + 1) + "/") + (calendar.get(Calendar.YEAR));
                JLabel dateLabel = new JLabel(output, SwingConstants.CENTER);
                dateLabel.setForeground(color2);
                dateLabel.setFont(new java.awt.Font(null, Font.BOLD, 16));
                calendarPanel.add(dateLabel);
                output = "Day: " + days[calendar.get(Calendar.DAY_OF_WEEK) - 1];
                JLabel dayLabel = new JLabel(output, SwingConstants.CENTER);
                dayLabel.setForeground(color2);
                dayLabel.setFont(new java.awt.Font(null, Font.BOLD, 16));
                calendarPanel.add(dayLabel);
                break;
            }
        }
        JLabel actionsLabel = new JLabel("Actions:", SwingConstants.CENTER);
        actionsLabel.setOpaque(true);
        actionsLabel.setBackground(color2);
        actionsLabel.setForeground(color1);
        actionsLabel.setFont(new java.awt.Font(null, Font.BOLD, 16));
        calendarPanel.add(actionsLabel);
        JLabel createCalendarEvents = new JLabel("Create a Calendar Event", SwingConstants.CENTER);
        createCalendarEvents.setForeground(color2);
        createCalendarEvents.setFont(new java.awt.Font(null, Font.BOLD, 16));
        createCalendarEvents.addMouseListener(new CreateCalendarEventsListener());
        calendarPanel.add(createCalendarEvents);
        JLabel viewEventsToday = new JLabel("View Today's Events", SwingConstants.CENTER);
        viewEventsToday.setForeground(color2);
        viewEventsToday.setFont(new java.awt.Font(null, Font.BOLD, 16));
        viewEventsToday.addMouseListener(new ViewEventsTodayListener());
        calendarPanel.add(viewEventsToday);
        tabbedPane.addTab("Calendar", calendarPanel);
        JPanel calculatorPanel = new JPanel();
        calculatorPanel.setLayout(new GridLayout(5, 5));
        calculatorPanel.setBackground(color1);
        JLabel basicCalculations = new JLabel("Basic Calculations:", SwingConstants.CENTER);
        basicCalculations.setOpaque(true);
        basicCalculations.setBackground(color2);
        basicCalculations.setForeground(color1);
        basicCalculations.setFont(new java.awt.Font(null, Font.BOLD, 16));
        calculatorPanel.add(basicCalculations);
        JLabel add2Numbers = new JLabel("Add", SwingConstants.CENTER);
        add2Numbers.setForeground(color2);
        add2Numbers.setFont(new java.awt.Font(null, Font.BOLD, 16));
        add2Numbers.addMouseListener(new Add2NumbersListener());
        calculatorPanel.add(add2Numbers);
        JLabel subtract2Numbers = new JLabel("Subtract", SwingConstants.CENTER);
        subtract2Numbers.setForeground(color2);
        subtract2Numbers.setFont(new java.awt.Font(null, Font.BOLD, 16));
        subtract2Numbers.addMouseListener(new Subtract2NumbersListener());
        calculatorPanel.add(subtract2Numbers);
        JLabel multiply2Numbers = new JLabel("Multiply", SwingConstants.CENTER);
        multiply2Numbers.setForeground(color2);
        multiply2Numbers.setFont(new java.awt.Font(null, Font.BOLD, 16));
        multiply2Numbers.addMouseListener(new Multiply2NumbersListener());
        calculatorPanel.add(multiply2Numbers);
        JLabel divide2Numbers = new JLabel("Divide", SwingConstants.CENTER);
        divide2Numbers.setForeground(color2);
        divide2Numbers.setFont(new java.awt.Font(null, Font.BOLD, 16));
        divide2Numbers.addMouseListener(new Divide2NumbersListener());
        calculatorPanel.add(divide2Numbers);
        JLabel complexCalculations = new JLabel("Complex Calculations:", SwingConstants.CENTER);
        complexCalculations.setOpaque(true);
        complexCalculations.setBackground(color2);
        complexCalculations.setForeground(color1);
        complexCalculations.setFont(new java.awt.Font(null, Font.BOLD, 16));
        calculatorPanel.add(complexCalculations);
        JLabel squareRoot = new JLabel("Square Root", SwingConstants.CENTER);
        squareRoot.setForeground(color2);
        squareRoot.setFont(new java.awt.Font(null, Font.BOLD, 16));
        squareRoot.addMouseListener(new SquareRootListener());
        calculatorPanel.add(squareRoot);
        JLabel cubeRoot = new JLabel("Cube Root", SwingConstants.CENTER);
        cubeRoot.setForeground(color2);
        cubeRoot.setFont(new java.awt.Font(null, Font.BOLD, 16));
        cubeRoot.addMouseListener(new CubeRootListener());
        calculatorPanel.add(cubeRoot);
        JLabel raiseToPower = new JLabel("Raise a Number to a Power", SwingConstants.CENTER);
        raiseToPower.setForeground(color2);
        raiseToPower.setFont(new java.awt.Font(null, Font.BOLD, 16));
        raiseToPower.addMouseListener(new RaiseToPowerListener());
        calculatorPanel.add(raiseToPower);
        JLabel quadraticEquation = new JLabel("Solve a Quadratic Equation", SwingConstants.CENTER);
        quadraticEquation.setForeground(color2);
        quadraticEquation.setFont(new java.awt.Font(null, Font.BOLD, 16));
        quadraticEquation.addMouseListener(new QuadraticEquationListener());
        calculatorPanel.add(quadraticEquation);
        JLabel emptySpace = new JLabel("", SwingConstants.CENTER);
        emptySpace.setOpaque(true);
        emptySpace.setBackground(color2);
        calculatorPanel.add(emptySpace);
        JLabel logBase10 = new JLabel("Log Base 10", SwingConstants.CENTER);
        logBase10.setForeground(color2);
        logBase10.setFont(new java.awt.Font(null, Font.BOLD, 16));
        logBase10.addMouseListener(new LogBase10Listener());
        calculatorPanel.add(logBase10);
        JLabel naturalLog = new JLabel("Natural Log", SwingConstants.CENTER);
        naturalLog.setForeground(color2);
        naturalLog.setFont(new java.awt.Font(null, Font.BOLD, 16));
        naturalLog.addMouseListener(new NaturalLogListener());
        calculatorPanel.add(naturalLog);
        JLabel computeUsingE = new JLabel("Compute Using E", SwingConstants.CENTER);
        computeUsingE.setForeground(color2);
        computeUsingE.setFont(new java.awt.Font(null, Font.BOLD, 16));
        computeUsingE.addMouseListener(new ComputeUsingEListener());
        calculatorPanel.add(computeUsingE);
        JLabel computeUsingPi = new JLabel("Compute Using Pi", SwingConstants.CENTER);
        computeUsingPi.setForeground(color2);
        computeUsingPi.setFont(new java.awt.Font(null, Font.BOLD, 16));
        computeUsingPi.addMouseListener(new ComputeUsingPiListener());
        calculatorPanel.add(computeUsingPi);
        JLabel trigonometry = new JLabel("Trigonometry:", SwingConstants.CENTER);
        trigonometry.setOpaque(true);
        trigonometry.setBackground(color2);
        trigonometry.setForeground(color1);
        trigonometry.setFont(new java.awt.Font(null, Font.BOLD, 16));
        calculatorPanel.add(trigonometry);
        JLabel sine = new JLabel("Sine", SwingConstants.CENTER);
        sine.setForeground(color2);
        sine.setFont(new java.awt.Font(null, Font.BOLD, 16));
        sine.addMouseListener(new SineListener());
        calculatorPanel.add(sine);
        JLabel cosine = new JLabel("Cosine", SwingConstants.CENTER);
        cosine.setForeground(color2);
        cosine.setFont(new java.awt.Font(null, Font.BOLD, 16));
        cosine.addMouseListener(new CosineListener());
        calculatorPanel.add(cosine);
        JLabel tangent = new JLabel("Tangent", SwingConstants.CENTER);
        tangent.setForeground(color2);
        tangent.setFont(new java.awt.Font(null, Font.BOLD, 16));
        tangent.addMouseListener(new TangentListener());
        calculatorPanel.add(tangent);
        JLabel emptySpace3 = new JLabel();
        calculatorPanel.add(emptySpace3);
        JLabel conversions = new JLabel("Conversions:", SwingConstants.CENTER);
        conversions.setOpaque(true);
        conversions.setBackground(color2);
        conversions.setForeground(color1);
        conversions.setFont(new java.awt.Font(null, Font.BOLD, 16));
        calculatorPanel.add(conversions);
        JLabel degreesToRadians = new JLabel("Degrees to Radians", SwingConstants.CENTER);
        degreesToRadians.setForeground(color2);
        degreesToRadians.setFont(new java.awt.Font(null, Font.BOLD, 16));
        degreesToRadians.addMouseListener(new DegreesToRadiansListener());
        calculatorPanel.add(degreesToRadians);
        JLabel radiansToDegrees = new JLabel("Radians to Degrees", SwingConstants.CENTER);
        radiansToDegrees.setForeground(color2);
        radiansToDegrees.setFont(new java.awt.Font(null, Font.BOLD, 16));
        radiansToDegrees.addMouseListener(new RadiansToDegreesListener());
        calculatorPanel.add(radiansToDegrees);
        tabbedPane.addTab("Calculator", calculatorPanel);
        JPanel notesPanel = new JPanel();
        notesPanel.setLayout(new GridLayout(2, 4));
        notesPanel.setBackground(color1);
        JLabel notesCreate = new JLabel("Create:", SwingConstants.CENTER);
        notesCreate.setOpaque(true);
        notesCreate.setBackground(color2);
        notesCreate.setForeground(color1);
        notesCreate.setFont(new java.awt.Font(null, Font.BOLD, 16));
        notesPanel.add(notesCreate);
        JLabel createNote = new JLabel("Create a Note", SwingConstants.CENTER);
        createNote.setForeground(color2);
        createNote.setFont(new java.awt.Font(null, Font.BOLD, 16));
        createNote.addMouseListener(new CreateNoteListener());
        notesPanel.add(createNote);
        JLabel importNotes = new JLabel("Import Notes", SwingConstants.CENTER);
        importNotes.setForeground(color2);
        importNotes.setFont(new java.awt.Font(null, Font.BOLD, 16));
        importNotes.addMouseListener(new ImportNotesListener());
        notesPanel.add(importNotes);
        JLabel editNote = new JLabel("Edit a Note", SwingConstants.CENTER);
        editNote.setForeground(color2);
        editNote.setFont(new java.awt.Font(null, Font.BOLD, 16));
        editNote.addMouseListener(new EditNoteListener());
        notesPanel.add(editNote);
        JLabel notesManage = new JLabel("Manage:", SwingConstants.CENTER);
        notesManage.setOpaque(true);
        notesManage.setBackground(color2);
        notesManage.setForeground(color1);
        notesManage.setFont(new java.awt.Font(null, Font.BOLD, 16));
        notesPanel.add(notesManage);
        JLabel renameNotes = new JLabel("Rename Notes", SwingConstants.CENTER);
        renameNotes.setForeground(color2);
        renameNotes.setFont(new java.awt.Font(null, Font.BOLD, 16));
        renameNotes.addMouseListener(new RenameNotesListener());
        notesPanel.add(renameNotes);
        JLabel searchNotes = new JLabel("Search Notes", SwingConstants.CENTER);
        searchNotes.setForeground(color2);
        searchNotes.setFont(new java.awt.Font(null, Font.BOLD, 16));
        searchNotes.addMouseListener(new SearchNotesListener());
        notesPanel.add(searchNotes);
        JLabel viewNotes = new JLabel("View Notes", SwingConstants.CENTER);
        viewNotes.setForeground(color2);
        viewNotes.setFont(new java.awt.Font(null, Font.BOLD, 16));
        viewNotes.addMouseListener(new ViewNotesListener());
        notesPanel.add(viewNotes);
        tabbedPane.addTab("Notes", notesPanel);
        JPanel photosPanel = new JPanel();
        photosPanel.setLayout(new GridLayout(2, 3));
        photosPanel.setBackground(color1);
        JLabel photosCreate = new JLabel("Create:", SwingConstants.CENTER);
        photosCreate.setOpaque(true);
        photosCreate.setBackground(color2);
        photosCreate.setForeground(color1);
        photosCreate.setFont(new java.awt.Font(null, Font.BOLD, 16));
        photosPanel.add(photosCreate);
        JLabel importPhotos = new JLabel("Import Photos", SwingConstants.CENTER);
        importPhotos.setForeground(color2);
        importPhotos.setFont(new java.awt.Font(null, Font.BOLD, 16));
        importPhotos.addMouseListener(new ImportPhotosListener());
        photosPanel.add(importPhotos);
        JLabel emptySpace4 = new JLabel();
        photosPanel.add(emptySpace4);
        JLabel photosManage = new JLabel("Manage:", SwingConstants.CENTER);
        photosManage.setOpaque(true);
        photosManage.setBackground(color2);
        photosManage.setForeground(color1);
        photosManage.setFont(new java.awt.Font(null, Font.BOLD, 16));
        photosPanel.add(photosManage);
        JLabel searchPhotos = new JLabel("Search Photos", SwingConstants.CENTER);
        searchPhotos.setForeground(color2);
        searchPhotos.setFont(new java.awt.Font(null, Font.BOLD, 16));
        searchPhotos.addMouseListener(new SearchPhotosListener());
        photosPanel.add(searchPhotos);
        JLabel viewPhotos = new JLabel("View Photos", SwingConstants.CENTER);
        viewPhotos.setForeground(color2);
        viewPhotos.setFont(new java.awt.Font(null, Font.BOLD, 16));
        viewPhotos.addMouseListener(new ViewPhotosListener());
        photosPanel.add(viewPhotos);
        tabbedPane.addTab("Photos", photosPanel);
        JPanel goldCenterPanel = new JPanel();
        goldCenterPanel.setLayout(new GridLayout(4, 3));
        goldCenterPanel.setBackground(color1);
        JLabel goldScriptForGames = new JLabel("GoldScript for Games:", SwingConstants.CENTER);
        goldScriptForGames.setOpaque(true);
        goldScriptForGames.setBackground(color2);
        goldScriptForGames.setForeground(color1);
        goldScriptForGames.setFont(new java.awt.Font(null, Font.BOLD, 16));
        goldCenterPanel.add(goldScriptForGames);
        JLabel goldScriptStep1 = new JLabel("Using GoldScript: Step 1", SwingConstants.CENTER);
        goldScriptStep1.setForeground(color2);
        goldScriptStep1.setFont(new java.awt.Font(null, Font.BOLD, 16));
        goldScriptStep1.addMouseListener(new GoldScriptStep1Listener());
        goldCenterPanel.add(goldScriptStep1);
        JLabel goldScriptStep2 = new JLabel("Using GoldScript: Step 2", SwingConstants.CENTER);
        goldScriptStep2.setForeground(color2);
        goldScriptStep2.setFont(new java.awt.Font(null, Font.BOLD, 16));
        goldScriptStep2.addMouseListener(new GoldScriptStep2Listener());
        goldCenterPanel.add(goldScriptStep2);
        JLabel goldScriptForApps = new JLabel("GoldScript for Apps:", SwingConstants.CENTER);
        goldScriptForApps.setOpaque(true);
        goldScriptForApps.setBackground(color2);
        goldScriptForApps.setForeground(color1);
        goldScriptForApps.setFont(new java.awt.Font(null, Font.BOLD, 16));
        goldCenterPanel.add(goldScriptForApps);
        JLabel step1 = new JLabel("Using GoldScript: Step 1", SwingConstants.CENTER);
        step1.setForeground(color2);
        step1.setFont(new java.awt.Font(null, Font.BOLD, 16));
        step1.addMouseListener(new Step1Listener());
        goldCenterPanel.add(step1);
        JLabel step2 = new JLabel("Using GoldScript: Step 2", SwingConstants.CENTER);
        step2.setForeground(color2);
        step2.setFont(new java.awt.Font(null, Font.BOLD, 16));
        step2.addMouseListener(new Step2Listener());
        goldCenterPanel.add(step2);
        JLabel goldScriptPoweredStuff = new JLabel("GoldScript-Powered Stuff:", SwingConstants.CENTER);
        goldScriptPoweredStuff.setOpaque(true);
        goldScriptPoweredStuff.setBackground(color2);
        goldScriptPoweredStuff.setForeground(color1);
        goldScriptPoweredStuff.setFont(new java.awt.Font(null, Font.BOLD, 16));
        goldCenterPanel.add(goldScriptPoweredStuff);
        JLabel goldGames = new JLabel("Gold Games", SwingConstants.CENTER);
        goldGames.setForeground(color2);
        goldGames.setFont(new java.awt.Font(null, Font.BOLD, 16));
        goldGames.addMouseListener(new GoldGamesListener());
        goldCenterPanel.add(goldGames);
        JLabel goldApps = new JLabel("Gold Apps", SwingConstants.CENTER);
        goldApps.setForeground(color2);
        goldApps.setFont(new java.awt.Font(null, Font.BOLD, 16));
        goldApps.addMouseListener(new GoldAppsListener());
        goldCenterPanel.add(goldApps);
        JLabel about = new JLabel("About:", SwingConstants.CENTER);
        about.setOpaque(true);
        about.setBackground(color2);
        about.setForeground(color1);
        about.setFont(new java.awt.Font(null, Font.BOLD, 16));
        goldCenterPanel.add(about);
        JLabel whatIsGoldScript = new JLabel("What is GoldScript?", SwingConstants.CENTER);
        whatIsGoldScript.setForeground(color2);
        whatIsGoldScript.setFont(new java.awt.Font(null, Font.BOLD, 16));
        whatIsGoldScript.addMouseListener(new WhatIsGoldScriptListener());
        goldCenterPanel.add(whatIsGoldScript);
        tabbedPane.addTab("Gold Center", goldCenterPanel);
        JPanel systemToolsPanel = new JPanel();
        systemToolsPanel.setLayout(new GridLayout(2, 3));
        systemToolsPanel.setBackground(color1);
        JLabel errorLogTitle = new JLabel("Error Log:", SwingConstants.CENTER);
        errorLogTitle.setOpaque(true);
        errorLogTitle.setBackground(color2);
        errorLogTitle.setForeground(color1);
        errorLogTitle.setFont(new java.awt.Font(null, Font.BOLD, 16));
        systemToolsPanel.add(errorLogTitle);
        JLabel viewErrors = new JLabel("View Errors", SwingConstants.CENTER);
        viewErrors.setForeground(color2);
        viewErrors.setFont(new java.awt.Font(null, Font.BOLD, 16));
        viewErrors.addMouseListener(new ErrorLogListener());
        systemToolsPanel.add(viewErrors);
        JLabel searchErrorLog = new JLabel("Search the Error Log", SwingConstants.CENTER);
        searchErrorLog.setForeground(color2);
        searchErrorLog.setFont(new java.awt.Font(null, Font.BOLD, 16));
        searchErrorLog.addMouseListener(new SearchErrorLogListener());
        systemToolsPanel.add(searchErrorLog);
        JLabel otherSystemTools = new JLabel("Other System Tools:", SwingConstants.CENTER);
        otherSystemTools.setOpaque(true);
        otherSystemTools.setBackground(color2);
        otherSystemTools.setForeground(color1);
        otherSystemTools.setFont(new java.awt.Font(null, Font.BOLD, 16));
        systemToolsPanel.add(otherSystemTools);
        JLabel commands = new JLabel("Commands", SwingConstants.CENTER);
        commands.setForeground(color2);
        commands.setFont(new java.awt.Font(null, Font.BOLD, 16));
        commands.addMouseListener(new CommandsListener());
        systemToolsPanel.add(commands);
        JLabel troubleshootProblems = new JLabel("Troubleshoot Problems", SwingConstants.CENTER);
        troubleshootProblems.setForeground(color2);
        troubleshootProblems.setFont(new java.awt.Font(null, Font.BOLD, 16));
        troubleshootProblems.addMouseListener(new TroubleshootProblemsListener());
        systemToolsPanel.add(troubleshootProblems);
        tabbedPane.addTab("System Tools", systemToolsPanel);
        JPanel settingsPanel = new JPanel();
        settingsPanel.setLayout(new GridLayout(2, 7));
        settingsPanel.setBackground(color1);
        JLabel generalSettings = new JLabel("General Settings:", SwingConstants.CENTER);
        generalSettings.setOpaque(true);
        generalSettings.setBackground(color2);
        generalSettings.setForeground(color1);
        generalSettings.setFont(new java.awt.Font(null, Font.BOLD, 16));
        settingsPanel.add(generalSettings);
        JLabel changeLanguage = new JLabel("Change Language", SwingConstants.CENTER);
        changeLanguage.setForeground(color2);
        changeLanguage.setFont(new java.awt.Font(null, Font.BOLD, 16));
        changeLanguage.addMouseListener(new ChangeLanguageListener());
        settingsPanel.add(changeLanguage);
        JLabel changeTheme = new JLabel("Change Theme", SwingConstants.CENTER);
        changeTheme.setForeground(color2);
        changeTheme.setFont(new java.awt.Font(null, Font.BOLD, 16));
        changeTheme.addMouseListener(new ChangeThemeListener());
        settingsPanel.add(changeTheme);
        JLabel changeUsername = new JLabel("Change Username", SwingConstants.CENTER);
        changeUsername.setForeground(color2);
        changeUsername.setFont(new java.awt.Font(null, Font.BOLD, 16));
        changeUsername.addMouseListener(new ChangeUsernameListener());
        settingsPanel.add(changeUsername);
        JLabel passwordSettings = new JLabel("Password Settings", SwingConstants.CENTER);
        passwordSettings.setForeground(color2);
        passwordSettings.setFont(new java.awt.Font(null, Font.BOLD, 16));
        passwordSettings.addMouseListener(new PasswordSettingsListener());
        settingsPanel.add(passwordSettings);
        JLabel emptySpace2 = new JLabel();
        settingsPanel.add(emptySpace2);
        JLabel emptySpace7 = new JLabel();
        settingsPanel.add(emptySpace7);
        JLabel appSettings = new JLabel("App Settings:", SwingConstants.CENTER);
        appSettings.setOpaque(true);
        appSettings.setBackground(color2);
        appSettings.setForeground(color1);
        appSettings.setFont(new java.awt.Font(null, Font.BOLD, 16));
        settingsPanel.add(appSettings);
        JLabel calendarSettings = new JLabel("Calendar Settings", SwingConstants.CENTER);
        calendarSettings.setForeground(color2);
        calendarSettings.setFont(new java.awt.Font(null, Font.BOLD, 16));
        calendarSettings.addMouseListener(new CalendarSettingsListener());
        settingsPanel.add(calendarSettings);
        JLabel calculatorSettings = new JLabel("Calculator Settings", SwingConstants.CENTER);
        calculatorSettings.setForeground(color2);
        calculatorSettings.setFont(new java.awt.Font(null, Font.BOLD, 16));
        calculatorSettings.addMouseListener(new CalculatorSettingsListener());
        settingsPanel.add(calculatorSettings);
        JLabel notesSettings = new JLabel("Notes Settings", SwingConstants.CENTER);
        notesSettings.setForeground(color2);
        notesSettings.setFont(new java.awt.Font(null, Font.BOLD, 16));
        notesSettings.addMouseListener(new NotesSettingsListener());
        settingsPanel.add(notesSettings);
        JLabel photosSettings = new JLabel("Photos Settings", SwingConstants.CENTER);
        photosSettings.setForeground(color2);
        photosSettings.setFont(new java.awt.Font(null, Font.BOLD, 16));
        photosSettings.addMouseListener(new PhotosSettingsListener());
        settingsPanel.add(photosSettings);
        JLabel searchSettings = new JLabel("Search Settings", SwingConstants.CENTER);
        searchSettings.setForeground(color2);
        searchSettings.setFont(new java.awt.Font(null, Font.BOLD, 16));
        searchSettings.addMouseListener(new SearchSettingsListener());
        settingsPanel.add(searchSettings);
        JLabel systemToolsSettings = new JLabel("System Tools Settings", SwingConstants.CENTER);
        systemToolsSettings.setForeground(color2);
        systemToolsSettings.setFont(new java.awt.Font(null, Font.BOLD, 16));
        systemToolsSettings.addMouseListener(new SystemToolsSettingsListener());
        settingsPanel.add(systemToolsSettings);
        tabbedPane.addTab("Settings", settingsPanel);
        JPanel aboutPanel = new JPanel();
        tabbedPane.addTab("About", aboutPanel);
        aboutPanel.setLayout(new GridLayout(3, 3));
        aboutPanel.setBackground(color1);
        JLabel emptySpace5 = new JLabel();
        aboutPanel.add(emptySpace5);
        JLabel title = new JLabel(name, SwingConstants.CENTER);
        title.setForeground(color2);
        title.setFont(new java.awt.Font(null, Font.BOLD, 22));
        aboutPanel.add(title);
        JLabel emptySpace6 = new JLabel();
        aboutPanel.add(emptySpace6);
        output = version;
        JLabel versionLabel = new JLabel(output, SwingConstants.CENTER);
        versionLabel.setForeground(color2);
        versionLabel.setFont(new java.awt.Font(null, Font.BOLD, 16));
        aboutPanel.add(versionLabel);
        output = "Build " + buildNumber;
        JLabel buildLabel = new JLabel(output, SwingConstants.CENTER);
        buildLabel.setForeground(color2);
        buildLabel.setFont(new java.awt.Font(null, Font.BOLD, 16));
        aboutPanel.add(buildLabel);
        output = "User: " + username;
        JLabel userLabel = new JLabel(output, SwingConstants.CENTER);
        userLabel.setForeground(color2);
        userLabel.setFont(new java.awt.Font(null, Font.BOLD, 16));
        aboutPanel.add(userLabel);
        JButton aboutPrograms = new JButton("About Programs");
        aboutPrograms.setFont(new java.awt.Font(null, Font.BOLD, 16));
        aboutPrograms.addActionListener(new AboutProgramsListener());
        aboutPanel.add(aboutPrograms);
        JButton historyOfGoldStars = new JButton("History of Gold Stars");
        historyOfGoldStars.setFont(new java.awt.Font(null, Font.BOLD, 16));
        historyOfGoldStars.addActionListener(new HistoryOfGoldStarsListener());
        aboutPanel.add(historyOfGoldStars);
        JButton whatsNew = new JButton("What's New in " + name);
        whatsNew.setFont(new java.awt.Font(null, Font.BOLD, 16));
        whatsNew.addActionListener(new WhatsNewListener());
        aboutPanel.add(whatsNew);
        frame.add(tabbedPane);
    }

    public static class GoldStarsTalkListener implements MouseListener {
        public void mousePressed(MouseEvent e) {
            goldStarsTalk("Hello, how are you?");
        }

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    public static class GoldStarsStoriesListener implements MouseListener {
        public void mousePressed(MouseEvent e) {
            goldStarsStories();
        }

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    public static class FilesListener implements MouseListener {
        public void mousePressed(MouseEvent e) {
            files();
        }

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    public static class NotificationsListener implements MouseListener {
        public void mousePressed(MouseEvent e) {
            notifications();
        }

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    public static class SearchListener implements MouseListener {
        public void mousePressed(MouseEvent e) {
            search();
        }

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    public static class HelpListener implements MouseListener {
        public void mousePressed(MouseEvent e) {
            help();
        }

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    public static class AboutProgramsListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            aboutPrograms();
        }
    }

    public static class HistoryOfGoldStarsListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            historyOfGoldStars();
        }
    }

    public static class WhatsNewListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            whatsNew();
        }
    }

    public static class CreateCalendarEventsListener implements MouseListener {
        public void mousePressed(MouseEvent e) {
            createCalendarEvents();
        }

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    public static class ViewEventsTodayListener implements MouseListener {
        public void mousePressed(MouseEvent e) {
            viewCalendarEvents();
        }

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    public static void viewCalendarEvents() {
        JFrame calendarFrame = new JFrame("Calendar Events");
        Calendar calendar = Calendar.getInstance();
        ArrayList<String> eventsToday = new ArrayList<String>();
        for (int i = 0; i < calendarEvents.size(); i++) {
            String temp = calendarEvents.get(i);
            int year, month, date;
            year = Integer.parseInt(temp.substring(0, 4));
            month = Integer.parseInt(temp.substring(4, 6));
            date = Integer.parseInt(temp.substring(6, 8));
            String event = temp.substring(8);
            if (year == calendar.get(Calendar.YEAR) && month == (calendar.get(Calendar.MONTH) + 1) && date == calendar.get(Calendar.DATE)) {
                eventsToday.add(event);
            }
        }
        if (eventsToday.isEmpty()) {
            output = "You don't have any calendar events today!";
            JOptionPane.showMessageDialog(null, output, "Calendar Events", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        calendarFrame.setSize(750, 375);
        calendarFrame.setLayout(new GridLayout(eventsToday.size(), 1));
        for (int i = 0; i < eventsToday.size(); i++) {
            JLabel event = new JLabel(eventsToday.get(i));
            event.setFont(new java.awt.Font(null, Font.PLAIN, 14));
            calendarFrame.getContentPane().add(event);
        }
        calendarFrame.setVisible(true);
    }

    public static void createCalendarEvents() {
        String dateAndEvent = "";
        int temp;
        String year = JOptionPane.showInputDialog(null, "Please enter the year of the event:", "Create a Calendar Event", JOptionPane.QUESTION_MESSAGE);
        if (year == null) {
            return;
        }
        dateAndEvent += year;
        try {
            temp = Integer.parseInt(year);
            if (temp < 1000 || temp > 9999) {
                errors.add("Error: Calendar");
                JOptionPane.showMessageDialog(null, "ERROR", "Create a Calendar Event", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (Exception e) {
            errors.add("Error: Calendar");
            JOptionPane.showMessageDialog(null, "ERROR", "Create a Calendar Event", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String month = JOptionPane.showInputDialog(null, "Please enter the month of the event:\n"
                + "For example, if it is in August, enter 08.", "Create a Calendar Event", JOptionPane.QUESTION_MESSAGE);
        if (month == null) {
            return;
        }
        dateAndEvent += month;
        try {
            temp = Integer.parseInt(month);
            if (temp < 1 || temp > 12) {
                errors.add("Error: Calendar");
                JOptionPane.showMessageDialog(null, "ERROR", "Create a Calendar Event", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (Exception e) {
            errors.add("Error: Calendar");
            JOptionPane.showMessageDialog(null, "ERROR", "Create a Calendar Event", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String day = JOptionPane.showInputDialog(null, "Please enter the date of the event:\n"
                + "For example, if it takes place on the 9th day of a month, enter 09.", "Create a Calendar Event", JOptionPane.QUESTION_MESSAGE);
        if (day == null) {
            return;
        }
        dateAndEvent += day;
        try {
            temp = Integer.parseInt(day);
            if (temp < 1 || temp > 31) {
                errors.add("Error: Calendar");
                JOptionPane.showMessageDialog(null, "ERROR", "Create a Calendar Event", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (Exception e) {
            errors.add("Error: Calendar");
            JOptionPane.showMessageDialog(null, "ERROR", "Create a Calendar Event", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String event = JOptionPane.showInputDialog(null, "Please enter the event:", "Create a Calendar Event", JOptionPane.QUESTION_MESSAGE);
        if (event == null) {
            return;
        }
        dateAndEvent += event;
        calendarEvents.add(dateAndEvent);
        PrintWriter out;
        try {
            out = new PrintWriter("calendarEvent" + (calendarEvents.size() - 1) + ".txt");
            out.println(calendarEvents.get(calendarEvents.size() - 1));
            out.close();
        } catch (FileNotFoundException e2) {

        }
        JOptionPane.showMessageDialog(null, "Event Saved", "Create a Calendar Event", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void downloadCalendarEvents() {
        int calendarEvent = 0;
        while (calendarEvent != -1) {
            try {
                calendarEvents.add(new Scanner(new File("calendarEvent" + calendarEvent + ".txt")).useDelimiter("\\Z").next());
                calendarEvent++;
            } catch (FileNotFoundException e) {
                calendarEvent = -1;
            }
        }
    }

    public static class Add2NumbersListener implements MouseListener {
        public void mousePressed(MouseEvent e) {
            add();
        }

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    public static class Subtract2NumbersListener implements MouseListener {
        public void mousePressed(MouseEvent e) {
            subtract2Numbers();
        }

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    public static class Multiply2NumbersListener implements MouseListener {
        public void mousePressed(MouseEvent e) {
            multiply2Numbers();
        }

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    public static class Divide2NumbersListener implements MouseListener {
        public void mousePressed(MouseEvent e) {
            divide2Numbers();
        }

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    public static class SquareRootListener implements MouseListener {
        public void mousePressed(MouseEvent e) {
            squareRoot();
        }

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    public static class CubeRootListener implements MouseListener {
        public void mousePressed(MouseEvent e) {
            cubeRoot();
        }

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    public static class RaiseToPowerListener implements MouseListener {
        public void mousePressed(MouseEvent e) {
            raiseANumberToAPower();
        }

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    public static class QuadraticEquationListener implements MouseListener {
        public void mousePressed(MouseEvent e) {
            quadraticEquation();
        }

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    public static class ComplexQuadraticEquationListener implements MouseListener {
        public void mousePressed(MouseEvent e) {
            complexQuadraticEquation();
        }

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    public static class AbsoluteValueListener implements MouseListener {
        public void mousePressed(MouseEvent e) {
            absoluteValue();
        }

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    public static class DegreesToRadiansListener implements MouseListener {
        public void mousePressed(MouseEvent e) {
            degreesToRadians();
        }

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    public static class RadiansToDegreesListener implements MouseListener {
        public void mousePressed(MouseEvent e) {
            radiansToDegrees();
        }

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    public static class LogBase10Listener implements MouseListener {
        public void mousePressed(MouseEvent e) {
            logBase10();
        }

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    public static class NaturalLogListener implements MouseListener {
        public void mousePressed(MouseEvent e) {
            naturalLog();
        }

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    public static class ComputeUsingEListener implements MouseListener {
        public void mousePressed(MouseEvent e) {
            computeUsingE();
        }

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    public static class ComputeUsingPiListener implements MouseListener {
        public void mousePressed(MouseEvent e) {
            computeUsingPi();
        }

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    public static class SineListener implements MouseListener {
        public void mousePressed(MouseEvent e) {
            sine();
        }

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    public static class CosineListener implements MouseListener {
        public void mousePressed(MouseEvent e) {
            cosine();
        }

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    public static class TangentListener implements MouseListener {
        public void mousePressed(MouseEvent e) {
            tangent();
        }

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    public static JTextField tf1, tf2, tf3, tf4, tf5, tf6, tf7, tf8, tf9, tf10;

    public static class AddListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String[] arrayOfStrings = new String[10];
            arrayOfStrings[0] = tf1.getText();
            arrayOfStrings[1] = tf2.getText();
            arrayOfStrings[2] = tf3.getText();
            arrayOfStrings[3] = tf4.getText();
            arrayOfStrings[4] = tf5.getText();
            arrayOfStrings[5] = tf6.getText();
            arrayOfStrings[6] = tf7.getText();
            arrayOfStrings[7] = tf8.getText();
            arrayOfStrings[8] = tf9.getText();
            arrayOfStrings[9] = tf10.getText();
            Double nums[] = new Double[10];
            for (int i = 0; i < nums.length; i++) {
                try {
                    nums[i] = Double.parseDouble(arrayOfStrings[i]);
                } catch (Exception e2) {
                    errors.add("Error: Calculator");
                    JOptionPane.showMessageDialog(null, "ERROR", "Calculator", JOptionPane.ERROR_MESSAGE);
                }
            }
            double sum = 0;
            for (int i = 0; i < nums.length; i++) {
                sum += nums[i];
            }
            JOptionPane.showMessageDialog(null, sum, "Addition", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void add() {
        JFrame additionFrame = new JFrame("Addition");
        additionFrame.setLayout(new GridLayout(11, 2));
        additionFrame.setSize(750, 375);
        JButton add = new JButton("Add");
        JLabel number1 = new JLabel("Number #1:");
        JLabel number2 = new JLabel("Number #2:");
        JLabel number3 = new JLabel("Number #3:");
        JLabel number4 = new JLabel("Number #4:");
        JLabel number5 = new JLabel("Number #5:");
        JLabel number6 = new JLabel("Number #6:");
        JLabel number7 = new JLabel("Number #7:");
        JLabel number8 = new JLabel("Number #8:");
        JLabel number9 = new JLabel("Number #9:");
        JLabel number10 = new JLabel("Number #10:");
        JLabel emptySpace = new JLabel("");
        tf1 = new JTextField("0");
        tf2 = new JTextField("0");
        tf3 = new JTextField("0");
        tf4 = new JTextField("0");
        tf5 = new JTextField("0");
        tf6 = new JTextField("0");
        tf7 = new JTextField("0");
        tf8 = new JTextField("0");
        tf9 = new JTextField("0");
        tf10 = new JTextField("0");
        additionFrame.add(number1);
        additionFrame.add(tf1);
        additionFrame.add(number2);
        additionFrame.add(tf2);
        additionFrame.add(number3);
        additionFrame.add(tf3);
        additionFrame.add(number4);
        additionFrame.add(tf4);
        additionFrame.add(number5);
        additionFrame.add(tf5);
        additionFrame.add(number6);
        additionFrame.add(tf6);
        additionFrame.add(number7);
        additionFrame.add(tf7);
        additionFrame.add(number8);
        additionFrame.add(tf8);
        additionFrame.add(number9);
        additionFrame.add(tf9);
        additionFrame.add(number10);
        additionFrame.add(tf10);
        additionFrame.add(emptySpace);
        additionFrame.add(add);
        additionFrame.setVisible(true);
        add.addActionListener(new AddListener());
    }

    public static class SubtractListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String[] arrayOfStrings = new String[10];
            arrayOfStrings[0] = tf1.getText();
            arrayOfStrings[1] = tf2.getText();
            arrayOfStrings[2] = tf3.getText();
            arrayOfStrings[3] = tf4.getText();
            arrayOfStrings[4] = tf5.getText();
            arrayOfStrings[5] = tf6.getText();
            arrayOfStrings[6] = tf7.getText();
            arrayOfStrings[7] = tf8.getText();
            arrayOfStrings[8] = tf9.getText();
            arrayOfStrings[9] = tf10.getText();
            Double nums[] = new Double[10];
            for (int i = 0; i < nums.length; i++) {
                try {
                    nums[i] = Double.parseDouble(arrayOfStrings[i]);
                } catch (Exception e2) {
                    errors.add("Error: Calculator");
                    JOptionPane.showMessageDialog(null, "ERROR", "Calculator", JOptionPane.ERROR_MESSAGE);
                }
            }
            double sum = nums[0];
            for (int i = 1; i < nums.length; i++) {
                sum -= nums[i];
            }
            JOptionPane.showMessageDialog(null, sum, "Subtraction", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void subtract2Numbers() {
        JFrame additionFrame = new JFrame("Subtraction");
        additionFrame.setLayout(new GridLayout(11, 2));
        additionFrame.setSize(750, 375);
        JButton add = new JButton("Subtract");
        JLabel number1 = new JLabel("Number #1:");
        JLabel number2 = new JLabel("Number #2:");
        JLabel number3 = new JLabel("Number #3:");
        JLabel number4 = new JLabel("Number #4:");
        JLabel number5 = new JLabel("Number #5:");
        JLabel number6 = new JLabel("Number #6:");
        JLabel number7 = new JLabel("Number #7:");
        JLabel number8 = new JLabel("Number #8:");
        JLabel number9 = new JLabel("Number #9:");
        JLabel number10 = new JLabel("Number #10:");
        JLabel emptySpace = new JLabel("");
        tf1 = new JTextField("0");
        tf2 = new JTextField("0");
        tf3 = new JTextField("0");
        tf4 = new JTextField("0");
        tf5 = new JTextField("0");
        tf6 = new JTextField("0");
        tf7 = new JTextField("0");
        tf8 = new JTextField("0");
        tf9 = new JTextField("0");
        tf10 = new JTextField("0");
        additionFrame.add(number1);
        additionFrame.add(tf1);
        additionFrame.add(number2);
        additionFrame.add(tf2);
        additionFrame.add(number3);
        additionFrame.add(tf3);
        additionFrame.add(number4);
        additionFrame.add(tf4);
        additionFrame.add(number5);
        additionFrame.add(tf5);
        additionFrame.add(number6);
        additionFrame.add(tf6);
        additionFrame.add(number7);
        additionFrame.add(tf7);
        additionFrame.add(number8);
        additionFrame.add(tf8);
        additionFrame.add(number9);
        additionFrame.add(tf9);
        additionFrame.add(number10);
        additionFrame.add(tf10);
        additionFrame.add(emptySpace);
        additionFrame.add(add);
        additionFrame.setVisible(true);
        add.addActionListener(new SubtractListener());
    }

    public static class MultiplyListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String[] arrayOfStrings = new String[10];
            arrayOfStrings[0] = tf1.getText();
            arrayOfStrings[1] = tf2.getText();
            arrayOfStrings[2] = tf3.getText();
            arrayOfStrings[3] = tf4.getText();
            arrayOfStrings[4] = tf5.getText();
            arrayOfStrings[5] = tf6.getText();
            arrayOfStrings[6] = tf7.getText();
            arrayOfStrings[7] = tf8.getText();
            arrayOfStrings[8] = tf9.getText();
            arrayOfStrings[9] = tf10.getText();
            Double nums[] = new Double[10];
            for (int i = 0; i < nums.length; i++) {
                try {
                    nums[i] = Double.parseDouble(arrayOfStrings[i]);
                } catch (Exception e2) {
                    errors.add("Error: Calculator");
                    JOptionPane.showMessageDialog(null, "ERROR", "Calculator", JOptionPane.ERROR_MESSAGE);
                }
            }
            double sum = 1;
            for (int i = 0; i < nums.length; i++) {
                sum *= nums[i];
            }
            JOptionPane.showMessageDialog(null, sum, "Multiplication", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void multiply2Numbers() {
        JFrame additionFrame = new JFrame("Multiplication");
        additionFrame.setLayout(new GridLayout(11, 2));
        additionFrame.setSize(750, 375);
        JButton add = new JButton("Multiply");
        JLabel number1 = new JLabel("Number #1:");
        JLabel number2 = new JLabel("Number #2:");
        JLabel number3 = new JLabel("Number #3:");
        JLabel number4 = new JLabel("Number #4:");
        JLabel number5 = new JLabel("Number #5:");
        JLabel number6 = new JLabel("Number #6:");
        JLabel number7 = new JLabel("Number #7:");
        JLabel number8 = new JLabel("Number #8:");
        JLabel number9 = new JLabel("Number #9:");
        JLabel number10 = new JLabel("Number #10:");
        JLabel emptySpace = new JLabel("");
        tf1 = new JTextField("1");
        tf2 = new JTextField("1");
        tf3 = new JTextField("1");
        tf4 = new JTextField("1");
        tf5 = new JTextField("1");
        tf6 = new JTextField("1");
        tf7 = new JTextField("1");
        tf8 = new JTextField("1");
        tf9 = new JTextField("1");
        tf10 = new JTextField("1");
        additionFrame.add(number1);
        additionFrame.add(tf1);
        additionFrame.add(number2);
        additionFrame.add(tf2);
        additionFrame.add(number3);
        additionFrame.add(tf3);
        additionFrame.add(number4);
        additionFrame.add(tf4);
        additionFrame.add(number5);
        additionFrame.add(tf5);
        additionFrame.add(number6);
        additionFrame.add(tf6);
        additionFrame.add(number7);
        additionFrame.add(tf7);
        additionFrame.add(number8);
        additionFrame.add(tf8);
        additionFrame.add(number9);
        additionFrame.add(tf9);
        additionFrame.add(number10);
        additionFrame.add(tf10);
        additionFrame.add(emptySpace);
        additionFrame.add(add);
        additionFrame.setVisible(true);
        add.addActionListener(new MultiplyListener());
    }

    public static class DivideListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String[] arrayOfStrings = new String[10];
            arrayOfStrings[0] = tf1.getText();
            arrayOfStrings[1] = tf2.getText();
            arrayOfStrings[2] = tf3.getText();
            arrayOfStrings[3] = tf4.getText();
            arrayOfStrings[4] = tf5.getText();
            arrayOfStrings[5] = tf6.getText();
            arrayOfStrings[6] = tf7.getText();
            arrayOfStrings[7] = tf8.getText();
            arrayOfStrings[8] = tf9.getText();
            arrayOfStrings[9] = tf10.getText();
            Double nums[] = new Double[10];
            for (int i = 0; i < nums.length; i++) {
                try {
                    nums[i] = Double.parseDouble(arrayOfStrings[i]);
                } catch (Exception e2) {
                    errors.add("Error: Calculator");
                    JOptionPane.showMessageDialog(null, "ERROR", "Calculator", JOptionPane.ERROR_MESSAGE);
                }
            }
            double sum = nums[0];
            for (int i = 1; i < nums.length; i++) {
                sum /= nums[i];
            }
            JOptionPane.showMessageDialog(null, sum, "Division", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void divide2Numbers() {
        JFrame additionFrame = new JFrame("Division");
        additionFrame.setLayout(new GridLayout(11, 2));
        additionFrame.setSize(750, 375);
        JButton add = new JButton("Divide");
        JLabel number1 = new JLabel("Number #1:");
        JLabel number2 = new JLabel("Number #2:");
        JLabel number3 = new JLabel("Number #3:");
        JLabel number4 = new JLabel("Number #4:");
        JLabel number5 = new JLabel("Number #5:");
        JLabel number6 = new JLabel("Number #6:");
        JLabel number7 = new JLabel("Number #7:");
        JLabel number8 = new JLabel("Number #8:");
        JLabel number9 = new JLabel("Number #9:");
        JLabel number10 = new JLabel("Number #10:");
        JLabel emptySpace = new JLabel("");
        tf1 = new JTextField("1");
        tf2 = new JTextField("1");
        tf3 = new JTextField("1");
        tf4 = new JTextField("1");
        tf5 = new JTextField("1");
        tf6 = new JTextField("1");
        tf7 = new JTextField("1");
        tf8 = new JTextField("1");
        tf9 = new JTextField("1");
        tf10 = new JTextField("1");
        additionFrame.add(number1);
        additionFrame.add(tf1);
        additionFrame.add(number2);
        additionFrame.add(tf2);
        additionFrame.add(number3);
        additionFrame.add(tf3);
        additionFrame.add(number4);
        additionFrame.add(tf4);
        additionFrame.add(number5);
        additionFrame.add(tf5);
        additionFrame.add(number6);
        additionFrame.add(tf6);
        additionFrame.add(number7);
        additionFrame.add(tf7);
        additionFrame.add(number8);
        additionFrame.add(tf8);
        additionFrame.add(number9);
        additionFrame.add(tf9);
        additionFrame.add(number10);
        additionFrame.add(tf10);
        additionFrame.add(emptySpace);
        additionFrame.add(add);
        additionFrame.setVisible(true);
        add.addActionListener(new DivideListener());
    }

    public static void squareRoot() {
        double sqrt;
        output = JOptionPane.showInputDialog("Please enter the number to find the square root of: ");
        if (output == null) {
            return;
        }
        try {
            sqrt = Double.parseDouble(output);
        } catch (Exception error) {
            errors.add("Error: Calculator");
            JOptionPane.showMessageDialog(null, "ERROR", "Calculator", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (sqrt < 0) {
            errors.add("Error: Calculator\n"
                    + "Error Description: Tried to find the square root of a negative number.");
            output = "ERROR:\n"
                    + "The square root of a negative number is not a real number.";
            JOptionPane.showMessageDialog(null, output, "Calculator", JOptionPane.ERROR_MESSAGE);
            return;
        }
        output = "The square root of " + sqrt + " is: " + Math.sqrt(sqrt);
        JOptionPane.showMessageDialog(null, output, "Square Root", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void cubeRoot() {
        double cbrt;
        output = JOptionPane.showInputDialog("Please enter the number to find the cube root of: ");
        if (output == null) {
            return;
        }
        try {
            cbrt = Double.parseDouble(output);
        } catch (Exception error) {
            errors.add("Error: Calculator");
            JOptionPane.showMessageDialog(null, "ERROR", "Calculator", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (cbrt < 0) {
            errors.add("Error: Calculator\n"
                    + "Error Description: Tried to find the cube root of a negative number.");
            output = "ERROR:\n"
                    + "The cube root of a negative number is not a real number.";
            JOptionPane.showMessageDialog(null, output, "Calculator", JOptionPane.ERROR_MESSAGE);
            return;
        }
        output = "The cube root of " + cbrt + " is: " + Math.cbrt(cbrt);
        JOptionPane.showMessageDialog(null, output, "Cube Root", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void computeUsingPi() {
        Double piTotal, xPi;
        String piChoice = "-1";
        while (!"0".equals(piChoice)) {
            piChoice = piMenu();
            switch (piChoice) {
                case "Pi + x": {
                    output = JOptionPane.showInputDialog("Please enter the value of x: ");
                    if (output == null) {
                        break;
                    }
                    try {
                        xPi = Double.parseDouble(output);
                    } catch (Exception error) {
                        errors.add("Error: Calculator");
                        JOptionPane.showMessageDialog(null, "ERROR", "Calculator", JOptionPane.ERROR_MESSAGE);
                        break;
                    }
                    piTotal = Math.PI + xPi;
                    output = "Pi + " + xPi + " = " + piTotal;
                    JOptionPane.showMessageDialog(null, output, "Calculator", JOptionPane.INFORMATION_MESSAGE);
                    break;
                }
                case "Pi - x": {
                    output = JOptionPane.showInputDialog("Please enter the value of x: ");
                    if (output == null) {
                        break;
                    }
                    try {
                        xPi = Double.parseDouble(output);
                    } catch (Exception error) {
                        errors.add("Error: Calculator");
                        JOptionPane.showMessageDialog(null, "ERROR", "Calculator", JOptionPane.ERROR_MESSAGE);
                        break;
                    }
                    piTotal = Math.PI - xPi;
                    output = "Pi - " + xPi + " = " + piTotal;
                    JOptionPane.showMessageDialog(null, output, "Calculator", JOptionPane.INFORMATION_MESSAGE);
                    break;
                }
                case "x - Pi": {
                    output = JOptionPane.showInputDialog("Please enter the value of x: ");
                    if (output == null) {
                        break;
                    }
                    try {
                        xPi = Double.parseDouble(output);
                    } catch (Exception error) {
                        errors.add("Error: Calculator");
                        JOptionPane.showMessageDialog(null, "ERROR", "Calculator", JOptionPane.ERROR_MESSAGE);
                        break;
                    }
                    piTotal = xPi - Math.PI;
                    output = xPi + " - Pi = " + piTotal;
                    JOptionPane.showMessageDialog(null, output, "Calculator", JOptionPane.INFORMATION_MESSAGE);
                    break;
                }
                case "Pi * x": {
                    output = JOptionPane.showInputDialog("Please enter the value of x: ");
                    if (output == null) {
                        break;
                    }
                    try {
                        xPi = Double.parseDouble(output);
                    } catch (Exception error) {
                        errors.add("Error: Calculator");
                        JOptionPane.showMessageDialog(null, "ERROR", "Calculator", JOptionPane.ERROR_MESSAGE);
                        break;
                    }
                    piTotal = Math.PI * xPi;
                    output = "Pi " + multiplicationSymbol + xPi + " = " + piTotal;
                    JOptionPane.showMessageDialog(null, output, "Calculator", JOptionPane.INFORMATION_MESSAGE);
                    break;
                }
                case "Pi / x": {
                    output = JOptionPane.showInputDialog("Please enter the value of x: ");
                    if (output == null) {
                        break;
                    }
                    try {
                        xPi = Double.parseDouble(output);
                    } catch (Exception error) {
                        errors.add("Error: Calculator");
                        JOptionPane.showMessageDialog(null, "ERROR", "Calculator", JOptionPane.ERROR_MESSAGE);
                        break;
                    }
                    piTotal = Math.PI / xPi;
                    output = "Pi " + divideSymbol + xPi + " = " + piTotal;
                    JOptionPane.showMessageDialog(null, output, "Calculator", JOptionPane.INFORMATION_MESSAGE);
                    break;
                }
                case "x / Pi": {
                    output = JOptionPane.showInputDialog("Please enter the value of x: ");
                    if (output == null) {
                        break;
                    }
                    try {
                        xPi = Double.parseDouble(output);
                    } catch (Exception error) {
                        errors.add("Error: Calculator");
                        JOptionPane.showMessageDialog(null, "ERROR", "Calculator", JOptionPane.ERROR_MESSAGE);
                        break;
                    }
                    piTotal = xPi / Math.PI;
                    output = xPi + divideSymbol + " Pi = " + piTotal;
                    JOptionPane.showMessageDialog(null, output, "Calculator", JOptionPane.INFORMATION_MESSAGE);
                    break;
                }
                case "Pi ^ x": {
                    output = JOptionPane.showInputDialog("Please enter the value of x: ");
                    if (output == null) {
                        break;
                    }
                    try {
                        xPi = Double.parseDouble(output);
                    } catch (Exception error) {
                        errors.add("Error: Calculator");
                        JOptionPane.showMessageDialog(null, "ERROR", "Calculator", JOptionPane.ERROR_MESSAGE);
                        break;
                    }
                    output = "Pi ^ " + xPi + " = " + Math.pow(Math.PI, xPi);
                    JOptionPane.showMessageDialog(null, output, "Calculator", JOptionPane.INFORMATION_MESSAGE);
                    break;
                }
                case "Display the Square Root of Pi": {
                    double answer = Math.sqrt(Math.PI);
                    JOptionPane.showMessageDialog(null, answer, "Calculator", JOptionPane.INFORMATION_MESSAGE);
                    break;
                }
                case "Display the Cube Root of Pi": {
                    double answer = Math.cbrt(Math.PI);
                    JOptionPane.showMessageDialog(null, answer, "Calculator", JOptionPane.INFORMATION_MESSAGE);
                    break;
                }
                case "0": {
                    break;
                }
                default: {
                    errors.add("Error: Calculator\n"
                            + "Error Description: Invalid Option Number");
                    output = "ERROR:\n"
                            + "Invalid Option Number";
                    JOptionPane.showMessageDialog(null, output, "Calculator", JOptionPane.ERROR_MESSAGE);
                    break;
                }
            }
        }
    }

    public static String piMenu() {
        try {
            Object[] options = new Object[9];
            options[0] = "Pi + x";
            options[1] = "Pi - x";
            options[2] = "x - Pi";
            options[3] = "Pi * x";
            options[4] = "Pi / x";
            options[5] = "x / Pi";
            options[6] = "Pi ^ x";
            options[7] = "Display the Square Root of Pi";
            options[8] = "Display the Cube Root of Pi";
            output = "Pi = " + Math.PI + "...\n"
                    + "How would you like to use pi?";
            String piOption = (String) JOptionPane.showInputDialog(null, output, "Compute Using Pi", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            if (piOption == null) {
                return "0";
            }
            return piOption;
        } catch (Exception error) {
            return "0";
        }
    }

    public static void raiseANumberToAPower() {
        double firstNumber, secondNumber;
        output = JOptionPane.showInputDialog("Please enter the number that will be raised to a power: ");
        if (output == null) {
            return;
        }
        try {
            firstNumber = Double.parseDouble(output);
        } catch (Exception error) {
            errors.add("Error: Calculator");
            JOptionPane.showMessageDialog(null, "ERROR", "Calculator", JOptionPane.ERROR_MESSAGE);
            return;
        }
        output = JOptionPane.showInputDialog("Please enter the number of the power to raise " + firstNumber + " to: ");
        if (output == null) {
            return;
        }
        try {
            secondNumber = Double.parseDouble(output);
        } catch (Exception error) {
            errors.add("Error: Calculator");
            JOptionPane.showMessageDialog(null, "ERROR", "Calculator", JOptionPane.ERROR_MESSAGE);
            return;
        }
        output = firstNumber + " raised to the power of " + secondNumber + " is:\n" + Math.pow(firstNumber, secondNumber);
        JOptionPane.showMessageDialog(null, output, "Raise a Number to a Power", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void quadraticEquation() {
        double a, b, c, x1, x2, quadSqrt;
        output = JOptionPane.showInputDialog("Please enter your quadratic equation in the form ax^2 + bx + c = 0.\n"
                + "You will enter the values of a, b, and c seperately.\n"
                + "a: ");
        if (output == null) {
            return;
        }
        try {
            a = Double.parseDouble(output);
        } catch (Exception error) {
            errors.add("Error: Calculator");
            JOptionPane.showMessageDialog(null, "ERROR", "Calculator", JOptionPane.ERROR_MESSAGE);
            return;
        }
        output = JOptionPane.showInputDialog("b: ");
        if (output == null) {
            return;
        }
        try {
            b = Double.parseDouble(output);
        } catch (Exception error) {
            errors.add("Error: Calculator");
            JOptionPane.showMessageDialog(null, "ERROR", "Calculator", JOptionPane.ERROR_MESSAGE);
            return;
        }
        output = JOptionPane.showInputDialog("c: ");
        if (output == null) {
            return;
        }
        try {
            c = Double.parseDouble(output);
        } catch (Exception error) {
            errors.add("Error: Calculator");
            JOptionPane.showMessageDialog(null, "ERROR", "Calculator", JOptionPane.ERROR_MESSAGE);
            return;
        }
        quadSqrt = b * b;
        quadSqrt = quadSqrt - (4 * a * c);
        quadSqrt = Math.sqrt(quadSqrt);
        x1 = (-1) * b;
        x2 = (-1) * b;
        x1 = x1 + quadSqrt;
        x2 = x2 - quadSqrt;
        x1 = x1 / (2 * a);
        x2 = x2 / (2 * a);
        if (x1 == x2) {
            output = "Solving the quadratic equation " + a + "x^2 + " + b + "x + " + c + " = 0...\n"
                    + "There is 1 possible solution: " + x1;
            JOptionPane.showMessageDialog(null, output, "Quadratic Equation", JOptionPane.INFORMATION_MESSAGE);
        } else {
            output = "Solving the quadratic equation " + a + "x^2 + " + b + "x + " + c + " = 0...\n"
                    + "There are 2 possible solutions: " + x1 + " and " + x2;
            JOptionPane.showMessageDialog(null, output, "Quadratic Equation", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void complexQuadraticEquation() {
        output = JOptionPane.showInputDialog("Enter the number of the quadratic equation to solve:\n"
                + "1: aix^2 + bx + c\n"
                + "2: ax^2 + bix + c\n"
                + "3: ax^2 + bx + ic\n");
        int complexSolverChoice;
        if (output == null) {
            return;
        }
        try {
            complexSolverChoice = Integer.parseInt(output);
        } catch (Exception error) {
            errors.add("Error: Calculator");
            JOptionPane.showMessageDialog(null, "ERROR", "Calculator", JOptionPane.ERROR_MESSAGE);
            return;
        }
        double a, b, c;
        output = JOptionPane.showInputDialog("a: ");
        if (output == null) {
            return;
        }
        try {
            a = Double.parseDouble(output);
        } catch (Exception error) {
            errors.add("Error: Calculator");
            JOptionPane.showMessageDialog(null, "ERROR", "Calculator", JOptionPane.ERROR_MESSAGE);
            return;
        }
        output = JOptionPane.showInputDialog("b: ");
        if (output == null) {
            return;
        }
        try {
            b = Double.parseDouble(output);
        } catch (Exception error) {
            errors.add("Error: Calculator");
            JOptionPane.showMessageDialog(null, "ERROR", "Calculator", JOptionPane.ERROR_MESSAGE);
            return;
        }
        output = JOptionPane.showInputDialog("c: ");
        if (output == null) {
            return;
        }
        try {
            c = Double.parseDouble(output);
        } catch (Exception error) {
            errors.add("Error: Calculator");
            JOptionPane.showMessageDialog(null, "ERROR", "Calculator", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (complexSolverChoice == 1) {
            output = "(-" + b + " (+/-) the square root of: " + (b * b) + " - " + (4 * a * c) + "i) / " + (2 * a) + "i";
            JOptionPane.showMessageDialog(null, output, "Complex Quadratic Equation", JOptionPane.INFORMATION_MESSAGE);
        } else if (complexSolverChoice == 2) {
            output = "(-" + b + "i (+/-) the square root of: " + (-1 * b * b) + " - " + (4 * a * c) + ") / " + (2 * a);
            JOptionPane.showMessageDialog(null, output, "Complex Quadratic Equation", JOptionPane.INFORMATION_MESSAGE);
        } else {
            output = "(-" + b + " (+/-) the square root of: " + (b * b) + " - " + (4 * a * c) + "i) / " + (2 * a);
            JOptionPane.showMessageDialog(null, output, "Complex Quadratic Equation", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void absoluteValue() {
        double numberToFindAbsValueOf;
        output = JOptionPane.showInputDialog("Please enter the number to find the absolute value of: ");
        if (output == null) {
            return;
        }
        try {
            numberToFindAbsValueOf = Double.parseDouble(output);
        } catch (Exception error) {
            errors.add("Error: Calculator");
            JOptionPane.showMessageDialog(null, "ERROR", "Calculator", JOptionPane.ERROR_MESSAGE);
            return;
        }
        double absValue;
        absValue = Math.abs(numberToFindAbsValueOf);
        output = "The absolute value of " + numberToFindAbsValueOf + " is: " + absValue;
        JOptionPane.showMessageDialog(null, output, "Absolute Value", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void computeUsingE() {
        double piTotal, xPi;
        String eChoice = "-1";
        while (!"0".equals(eChoice)) {
            eChoice = eMenu();
            switch (eChoice) {
                case "E + x": {
                    output = JOptionPane.showInputDialog("Please enter the value of x: ");
                    if (output == null) {
                        break;
                    }
                    try {
                        xPi = Double.parseDouble(output);
                    } catch (Exception error) {
                        errors.add("Error: Calculator");
                        JOptionPane.showMessageDialog(null, "ERROR", "Calculator", JOptionPane.ERROR_MESSAGE);
                        break;
                    }
                    piTotal = Math.E + xPi;
                    output = "E + " + xPi + " = " + piTotal;
                    JOptionPane.showMessageDialog(null, output, "Calculator", JOptionPane.INFORMATION_MESSAGE);
                    break;
                }
                case "E - x": {
                    output = JOptionPane.showInputDialog("Please enter the value of x: ");
                    if (output == null) {
                        break;
                    }
                    try {
                        xPi = Double.parseDouble(output);
                    } catch (Exception error) {
                        errors.add("Error: Calculator");
                        JOptionPane.showMessageDialog(null, "ERROR", "Calculator", JOptionPane.ERROR_MESSAGE);
                        break;
                    }
                    piTotal = Math.E - xPi;
                    output = "E - " + xPi + " = " + piTotal;
                    JOptionPane.showMessageDialog(null, output, "Calculator", JOptionPane.INFORMATION_MESSAGE);
                    break;
                }
                case "x - E": {
                    output = JOptionPane.showInputDialog("Please enter the value of x: ");
                    if (output == null) {
                        break;
                    }
                    try {
                        xPi = Double.parseDouble(output);
                    } catch (Exception error) {
                        errors.add("Error: Calculator");
                        JOptionPane.showMessageDialog(null, "ERROR", "Calculator", JOptionPane.ERROR_MESSAGE);
                        break;
                    }
                    piTotal = xPi - Math.E;
                    output = xPi + " - E = " + piTotal;
                    JOptionPane.showMessageDialog(null, output, "Calculator", JOptionPane.INFORMATION_MESSAGE);
                    break;
                }
                case "E * x": {
                    output = JOptionPane.showInputDialog("Please enter the value of x: ");
                    if (output == null) {
                        break;
                    }
                    try {
                        xPi = Double.parseDouble(output);
                    } catch (Exception error) {
                        errors.add("Error: Calculator");
                        JOptionPane.showMessageDialog(null, "ERROR", "Calculator", JOptionPane.ERROR_MESSAGE);
                        break;
                    }
                    piTotal = Math.E * xPi;
                    output = "E " + multiplicationSymbol + xPi + " = " + piTotal;
                    JOptionPane.showMessageDialog(null, output, "Calculator", JOptionPane.INFORMATION_MESSAGE);
                    break;
                }
                case "E / x": {
                    output = JOptionPane.showInputDialog("Please enter the value of x: ");
                    if (output == null) {
                        break;
                    }
                    try {
                        xPi = Double.parseDouble(output);
                    } catch (Exception error) {
                        errors.add("Error: Calculator");
                        JOptionPane.showMessageDialog(null, "ERROR", "Calculator", JOptionPane.ERROR_MESSAGE);
                        break;
                    }
                    piTotal = Math.E / xPi;
                    output = "E " + divideSymbol + xPi + " = " + piTotal;
                    JOptionPane.showMessageDialog(null, output, "Calculator", JOptionPane.INFORMATION_MESSAGE);
                    break;
                }
                case "x / E": {
                    output = JOptionPane.showInputDialog("Please enter the value of x: ");
                    if (output == null) {
                        break;
                    }
                    try {
                        xPi = Double.parseDouble(output);
                    } catch (Exception error) {
                        errors.add("Error: Calculator");
                        JOptionPane.showMessageDialog(null, "ERROR", "Calculator", JOptionPane.ERROR_MESSAGE);
                        break;
                    }
                    piTotal = xPi / Math.E;
                    output = xPi + divideSymbol + " E = " + piTotal;
                    JOptionPane.showMessageDialog(null, output, "Calculator", JOptionPane.INFORMATION_MESSAGE);
                    break;
                }
                case "E ^ x": {
                    output = JOptionPane.showInputDialog("Please enter the value of x: ");
                    if (output == null) {
                        break;
                    }
                    try {
                        xPi = Double.parseDouble(output);
                    } catch (Exception error) {
                        errors.add("Error: Calculator");
                        JOptionPane.showMessageDialog(null, "ERROR", "Calculator", JOptionPane.ERROR_MESSAGE);
                        break;
                    }
                    output = "E ^ " + xPi + " = " + Math.pow(Math.E, xPi);
                    JOptionPane.showMessageDialog(null, output, "Calculator", JOptionPane.INFORMATION_MESSAGE);
                    break;
                }
                case "Display the Square Root of E": {
                    double answer = Math.sqrt(Math.E);
                    JOptionPane.showMessageDialog(null, answer, "Calculator", JOptionPane.INFORMATION_MESSAGE);
                    break;
                }
                case "Display the Cube Root of E": {
                    double answer = Math.sqrt(Math.E);
                    JOptionPane.showMessageDialog(null, answer, "Calculator", JOptionPane.INFORMATION_MESSAGE);
                    break;
                }
                case "0": {
                    break;
                }
                case "": {
                    errors.add("Error: Calculator\n"
                            + "Error Description: No Option Number Entered");
                    output = "ERROR:\n"
                            + "No Option Number Entered";
                    JOptionPane.showMessageDialog(null, output, "Calculator", JOptionPane.ERROR_MESSAGE);
                    break;
                }
                default: {
                    errors.add("Error: Calculator\n"
                            + "Error Description: No Option Number Entered");
                    output = "ERROR:\n"
                            + "Invalid Option Number";
                    JOptionPane.showMessageDialog(null, output, "Calculator", JOptionPane.ERROR_MESSAGE);
                    break;
                }
            }
        }
    }

    public static String eMenu() {
        try {
            Object[] options = new Object[9];
            options[0] = "E + x";
            options[1] = "E - x";
            options[2] = "x - E";
            options[3] = "E * x";
            options[4] = "E / x";
            options[5] = "x / E";
            options[6] = "E ^ x";
            options[7] = "Display the Square Root of E";
            options[8] = "Display the Cube Root of E";
            output = "E = " + Math.E + "...\n"
                    + "How would you like to use E?";
            String eOption = (String) JOptionPane.showInputDialog(null, output, "Compute Using E", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            if (eOption == null) {
                return "0";
            }
            return eOption;
        } catch (Exception error) {
            return "0";
        }
    }

    public static void degreesToRadians() {
        double firstNumber, secondNumber;
        output = JOptionPane.showInputDialog("Please enter the number of degrees:");
        if (output == null) {
            return;
        }
        try {
            firstNumber = Double.parseDouble(output);
        } catch (Exception error) {
            errors.add("Error: Calculator");
            JOptionPane.showMessageDialog(null, "ERROR", "Calculator", JOptionPane.ERROR_MESSAGE);
            return;
        }
        secondNumber = Math.toRadians(firstNumber);
        output = firstNumber + " Degrees ≈\n" + secondNumber + " Radians";
        JOptionPane.showMessageDialog(null, output, "Degrees to Radians", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void radiansToDegrees() {
        double firstNumber, secondNumber;
        output = JOptionPane.showInputDialog("Please enter the number of radians:");
        if (output == null) {
            return;
        }
        try {
            firstNumber = Double.parseDouble(output);
        } catch (Exception error) {
            errors.add("Error: Calculator");
            JOptionPane.showMessageDialog(null, "ERROR", "Calculator", JOptionPane.ERROR_MESSAGE);
            return;
        }
        secondNumber = Math.toDegrees(firstNumber);
        output = firstNumber + " Radians ≈\n" + secondNumber + " Degrees";
        JOptionPane.showMessageDialog(null, output, "Radians to Degrees", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void logBase10() {
        double firstNumber, secondNumber;
        output = JOptionPane.showInputDialog("Please enter the number to find the log base 10 of:");
        if (output == null) {
            return;
        }
        try {
            firstNumber = Double.parseDouble(output);
        } catch (Exception error) {
            errors.add("Error: Calculator");
            JOptionPane.showMessageDialog(null, "ERROR", "Calculator", JOptionPane.ERROR_MESSAGE);
            return;
        }
        secondNumber = Math.log10(firstNumber);
        output = "The log base 10 of " + firstNumber + " is: " + secondNumber;
        JOptionPane.showMessageDialog(null, output, "Log Base 10", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void naturalLog() {
        double firstNumber, secondNumber;
        output = JOptionPane.showInputDialog("Please enter the number to find the natural log of:");
        if (output == null) {
            return;
        }
        try {
            firstNumber = Double.parseDouble(output);
        } catch (Exception error) {
            errors.add("Error: Calculator");
            JOptionPane.showMessageDialog(null, "ERROR", "Calculator", JOptionPane.ERROR_MESSAGE);
            return;
        }
        secondNumber = Math.log(firstNumber);
        output = "The natural log of " + firstNumber + " is: " + secondNumber;
        JOptionPane.showMessageDialog(null, output, "Natural Log", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void sine() {
        double input;
        output = JOptionPane.showInputDialog("Please enter the number of radians to find the sine of:");
        if (output == null) {
            return;
        }
        try {
            input = Double.parseDouble(output);
        } catch (Exception error) {
            errors.add("Error: Calculator");
            JOptionPane.showMessageDialog(null, "ERROR", "Calculator", JOptionPane.ERROR_MESSAGE);
            return;
        }
        double sine = Math.sin(input);
        output = "The sine of " + input + " radians is: " + sine;
        JOptionPane.showMessageDialog(null, output, "Sine", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void cosine() {
        double input;
        output = JOptionPane.showInputDialog("Please enter the number of radians to find the cosine of:");
        if (output == null) {
            return;
        }
        try {
            input = Double.parseDouble(output);
        } catch (Exception error) {
            errors.add("Error: Calculator");
            JOptionPane.showMessageDialog(null, "ERROR", "Calculator", JOptionPane.ERROR_MESSAGE);
            return;
        }
        double cosine = Math.cos(input);
        output = "The cosine of " + input + " radians is: " + cosine;
        JOptionPane.showMessageDialog(null, output, "Cosine", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void tangent() {
        double input;
        output = JOptionPane.showInputDialog("Please enter the number of radians to find the tangent of:");
        if (output == null) {
            return;
        }
        try {
            input = Double.parseDouble(output);
        } catch (Exception error) {
            errors.add("Error: Calculator");
            JOptionPane.showMessageDialog(null, "ERROR", "Calculator", JOptionPane.ERROR_MESSAGE);
            return;
        }
        double tangent = Math.tan(input);
        output = "The tangent of " + input + " radians is: " + tangent;
        JOptionPane.showMessageDialog(null, output, "Tangent", JOptionPane.INFORMATION_MESSAGE);
    }

    public static class CreateNoteListener implements MouseListener {
        public void mousePressed(MouseEvent e) {
            createNote();
        }

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    public static class EditNoteListener implements MouseListener {
        public void mousePressed(MouseEvent e) {
            editNote();
        }

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    public static class ViewNotesListener implements MouseListener {
        public void mousePressed(MouseEvent e) {
            viewNote();
        }

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    public static class ImportNotesListener implements MouseListener {
        public void mousePressed(MouseEvent e) {
            importNotes();
        }

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    public static class RenameNotesListener implements MouseListener {
        public void mousePressed(MouseEvent e) {
            renameNotes();
        }

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    public static class SearchNotesListener implements MouseListener {
        public void mousePressed(MouseEvent e) {
            searchNotes();
        }

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    public static void downloadNotes() {
        int notesNum = 0;
        while (notesNum != -1) {
            try {
                notes.add(new Scanner(new File("note" + notesNum + ".txt")).useDelimiter("\\Z").next());
                notesNum++;
            } catch (FileNotFoundException e) {
                notesNum = -1;
            }
        }
    }

    public static void downloadNoteNames() {
        int notesNum = 0;
        while (notesNum != -1) {
            try {
                noteNames.add(new Scanner(new File("noteName" + notesNum + ".txt")).useDelimiter("\\Z").next());
                notesNum++;
            } catch (FileNotFoundException e) {
                notesNum = -1;
            }
        }
    }

    public static JTextField createNotesTextField;

    public static void createNote() {
        createNotesTextField = new JTextField("");
        if (passwordProtectedNotes) {
            boolean access = password();
            if (access == false) {
                return;
            }
        }
        JFrame createNoteFrame = new JFrame("Create a Note");
        createNoteFrame.setSize(450, 200);
        createNoteFrame.add(createNotesTextField);
        createNoteFrame.setVisible(true);
        createNotesTextField.addActionListener(new CreateNoteNewListener());
    }

    public static class CreateNoteNewListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            processNote(createNotesTextField.getText());
            createNotesTextField.setText("");
        }
    }

    public static void processNote(String tempNotesInput) {
        if (tempNotesInput != null) {
            notes.add(tempNotesInput);
            noteNames.add("Note #" + notes.size());
            JOptionPane.showMessageDialog(null, "Note Saved", "Notes", JOptionPane.INFORMATION_MESSAGE);
            PrintWriter out;
            try {
                out = new PrintWriter("note" + (notes.size() - 1) + ".txt");
                out.println(notes.get(notes.size() - 1));
                out.close();
            } catch (FileNotFoundException e2) {

            }
            PrintWriter out2;
            try {
                out2 = new PrintWriter("noteName" + (noteNames.size() - 1) + ".txt");
                out2.println(noteNames.get(noteNames.size() - 1));
                out2.close();
            } catch (FileNotFoundException e3) {

            }
        }
    }

    public static void viewNote() {
        JFrame viewNotesFrame = new JFrame("My Notes");
        viewNotesFrame.setSize(750, 375);
        viewNotesFrame.getContentPane().setBackground(color1);
        viewNotesFrame.setExtendedState(viewNotesFrame.MAXIMIZED_BOTH);
        ArrayList<String> files = new ArrayList<String>();
        for (int i = 0; i < noteNames.size(); i++) {
            files.add(noteNames.get(i));
        }
        JLabel[] fileLabels = new JLabel[files.size()];
        for (int i = 0; i < noteNames.size(); i++) {
            fileLabels[i] = new JLabel(noteNames.get(i), SwingConstants.CENTER);
            fileLabels[i].addMouseListener(new FileListener(notes.get(i), false));
        }
        if (fileLabels.length == 0) {
            JLabel noFiles = new JLabel("You haven't saved any notes to Gold Stars yet!", SwingConstants.CENTER);
            noFiles.setFont(new java.awt.Font(null, Font.BOLD, 16));
            noFiles.setForeground(color2);
            viewNotesFrame.add(noFiles);
        } else {
            for (int i = 0; i < fileLabels.length; i++) {
                viewNotesFrame.setLayout(new GridLayout(((noteNames.size() - 1) / 3) + 1, 3));
                fileLabels[i].setFont(new java.awt.Font(null, Font.BOLD, 16));
                fileLabels[i].setForeground(color2);
                viewNotesFrame.add(fileLabels[i]);
            }
        }
        viewNotesFrame.setVisible(true);
    }

    public static JTextField editNotesTextField;

    public static void editNote() {
        editNotesTextField = new JTextField();
        if (passwordProtectedNotes) {
            boolean access = password();
            if (access == false) {
                return;
            }
        }
        if (notes.isEmpty()) {
            output = "You need to create a note before you can edit notes.";
            JOptionPane.showMessageDialog(null, output, "Notes", JOptionPane.INFORMATION_MESSAGE);
        } else {
            output = JOptionPane.showInputDialog(null, "Please enter the number of a note to edit.\n"
                    + "You currently have " + notes.size() + " note(s).");
            if (output == null) {
                return;
            }
            try {
                int editNotesChoice = Integer.parseInt(output);
                notesTemp = editNotesChoice;
                JFrame createNoteFrame = new JFrame("Create a Note");
                createNoteFrame.setSize(450, 200);
                createNoteFrame.add(editNotesTextField);
                createNoteFrame.setVisible(true);
                editNotesTextField.setText(notes.get((editNotesChoice - 1)));
                editNotesTextField.addActionListener(new EditNoteNewListener());
            } catch (Exception error) {
                errors.add("Error: Notes");
                JOptionPane.showMessageDialog(null, "ERROR", "Notes", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
    }

    public static class EditNoteNewListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            notes.set((notesTemp - 1), editNotesTextField.getText());
            editNotesTextField.setText("");
            JOptionPane.showMessageDialog(null, "Note Saved", "Notes", JOptionPane.INFORMATION_MESSAGE);
            PrintWriter out;
            try {
                out = new PrintWriter("note" + (notesTemp - 1) + ".txt");
                out.append(notes.get(notesTemp - 1));
                out.close();
            } catch (FileNotFoundException e2) {

            }
        }
    }

    public static void searchNotes() {
        if (passwordProtectedNotes) {
            boolean access = password();
            if (access == false) {
                return;
            }
        }
        if (notes.isEmpty()) {
            output = "You need to create a note before you can search notes.";
            JOptionPane.showMessageDialog(null, output, "Notes", JOptionPane.INFORMATION_MESSAGE);
        } else {
            ArrayList<String> searchResults = new ArrayList<String>();
            String notesSearchTerm = JOptionPane.showInputDialog(null, "Please enter your search term:");
            if (notesSearchTerm == null) {
                return;
            }
            String lowerCaseSearchTerm = notesSearchTerm;
            if (notesSearchCaseSensitive == false) {
                lowerCaseSearchTerm = notesSearchTerm.toLowerCase();
            }
            for (int i = 0; i < notes.size(); i++) {
                if (notesSearchCaseSensitive == false) {
                    String temp = notes.get(i);
                    temp = temp.toLowerCase();
                    if (temp.indexOf(lowerCaseSearchTerm) >= 0) {
                        searchResults.add(notes.get(i));
                    }
                } else {
                    if (notes.get(i).indexOf(notesSearchTerm) >= 0) {
                        searchResults.add(notes.get(i));
                    }
                }
            }
            String notesSearchResultInput = "-1";
            while (!"0".equals(notesSearchResultInput)) {
                if (searchResults.isEmpty()) {
                    output = "No search results were found.";
                    JOptionPane.showMessageDialog(null, output, "Notes", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                if (searchResults.size() == 1) {
                    notesSearchResultInput = JOptionPane.showInputDialog(searchResults.size() + " result was found.\n"
                            + "Please enter the number of the search result to view, or enter 0 or press Cancel to return to the Notes Menu.");
                } else {
                    notesSearchResultInput = JOptionPane.showInputDialog(searchResults.size() + " results were found.\n"
                            + "Please enter the number of the search result to view, or enter 0 or press Cancel to return to the Notes Menu.");
                }
                if ((notesSearchResultInput == null) || ("0".equals(notesSearchResultInput))) {
                    break;
                }
                try {
                    int searchTermViewIndex = Integer.parseInt(notesSearchResultInput);
                    searchTermViewIndex--;
                    output = "Search Term: " + notesSearchTerm + "\n"
                            + "Search Result " + (searchTermViewIndex + 1) + " of " + searchResults.size() + ":\n"
                            + searchResults.get(searchTermViewIndex);
                    JOptionPane.showMessageDialog(null, output, "Notes", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception error) {
                    errors.add("Error: Notes");
                    JOptionPane.showMessageDialog(null, "ERROR", "Notes", JOptionPane.ERROR_MESSAGE);
                    break;
                }
            }
        }
    }

    public static void importNotes() {
        if (passwordProtectedNotes) {
            boolean access = password();
            if (access == false) {
                return;
            }
        }
        output = "Place the note that you want to import in the folder that Gold Stars is in.\n"
                + "Make sure that the note is in a .txt file.\n"
                + "Now, please enter the name of the .txt file (case sensitive):";
        String nameOfNote = JOptionPane.showInputDialog(null, output, "Import Notes", JOptionPane.QUESTION_MESSAGE);
        String temp;
        try {
            temp = new Scanner(new File(nameOfNote + ".txt")).useDelimiter("\\Z").next();
        } catch (FileNotFoundException e) {
            errors.add("Error: Notes");
            output = "ERROR:\n"
                    + "Note Not Found";
            JOptionPane.showMessageDialog(null, output, "Notes", JOptionPane.ERROR_MESSAGE);
            return;
        }
        processNote(temp);
    }

    public static void renameNotes() {
        if (passwordProtectedNotes) {
            boolean access = password();
            if (access == false) {
                return;
            }
        }
        output = JOptionPane.showInputDialog("Please enter the number of a note to rename.\n"
                + "You currently have " + notes.size() + " notes.");
        if (output == null) {
            return;
        }
        int noteToRename;
        try {
            noteToRename = Integer.parseInt(output);
        } catch (Exception e) {
            return;
        }
        output = JOptionPane.showInputDialog("Please enter the new name of " + noteNames.get(noteToRename - 1));
        if (output == null) {
            return;
        }
        noteNames.set(noteToRename - 1, output);
        PrintWriter out;
        try {
            out = new PrintWriter("noteName" + (noteToRename - 1) + ".txt");
            out.append(noteNames.get(noteToRename - 1));
            out.close();
        } catch (FileNotFoundException e2) {

        }
        JOptionPane.showMessageDialog(null, "Note Saved", "Notes", JOptionPane.INFORMATION_MESSAGE);
    }

    public static class ImportPhotosListener implements MouseListener {
        public void mousePressed(MouseEvent e) {
            importPhotos();
        }

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    public static class ViewPhotosListener implements MouseListener {
        public void mousePressed(MouseEvent e) {
            viewPhotos();
        }

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    public static class SearchPhotosListener implements MouseListener {
        public void mousePressed(MouseEvent e) {
            searchPhotos();
        }

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    public static void downloadPhotos() {
        int photoNum = 0;
        while (photoNum != -1) {
            try {
                photoNames.add(new Scanner(new File("photo" + photoNum + ".txt")).useDelimiter("\\Z").next());
                photoNum++;
            } catch (FileNotFoundException e) {
                photoNum = -1;
            }
        }
    }

    public static void searchPhotos() {
        if (passwordProtectedPhotos) {
            boolean access = password();
            if (access == false) {
                return;
            }
        }
        if (photoNames.isEmpty()) {
            output = "You need to import a photo before you can search photos.";
            JOptionPane.showMessageDialog(null, output, "Photos", JOptionPane.INFORMATION_MESSAGE);
        } else {
            ArrayList<String> searchResults = new ArrayList<String>();
            String photosSearchTerm = JOptionPane.showInputDialog(null, "Please enter your search term:");
            if (photosSearchTerm == null) {
                return;
            }
            String lowerCaseSearchTerm = photosSearchTerm;
            if (photosSearchCaseSensitive == false) {
                lowerCaseSearchTerm = photosSearchTerm.toLowerCase();
            }
            for (int i = 0; i < photoNames.size(); i++) {
                if (photosSearchCaseSensitive == false) {
                    String temp = photoNames.get(i);
                    temp = temp.toLowerCase();
                    if (temp.indexOf(lowerCaseSearchTerm) >= 0) {
                        searchResults.add(photoNames.get(i));
                    }
                } else {
                    if (photoNames.get(i).indexOf(photosSearchTerm) >= 0) {
                        searchResults.add(photoNames.get(i));
                    }
                }
            }
            String photosSearchResultInput = "-1";
            while (!"0".equals(photosSearchResultInput)) {
                if (searchResults.isEmpty()) {
                    output = "No search results were found.";
                    JOptionPane.showMessageDialog(null, output, "Photos", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                if (searchResults.size() == 1) {
                    photosSearchResultInput = JOptionPane.showInputDialog(searchResults.size() + " result was found.\n"
                            + "Please enter the number of the search result to view:");
                } else {
                    photosSearchResultInput = JOptionPane.showInputDialog(searchResults.size() + " results were found.\n"
                            + "Please enter the number of the search result to view:");
                }
                if ((photosSearchResultInput == null) || ("0".equals(photosSearchResultInput))) {
                    break;
                }
                try {
                    int searchTermViewIndex = Integer.parseInt(photosSearchResultInput);
                    searchTermViewIndex--;
                    JFrame photoFrame = new JFrame("Photos");
                    photoFrame.setVisible(true);
                    photoFrame.setSize(1000, 600);
                    ImageIcon image = new ImageIcon(searchResults.get(searchTermViewIndex));
                    JLabel photo = new JLabel(image);
                    photoFrame.add(photo);
                } catch (Exception error) {
                    errors.add("Error: Photos");
                    JOptionPane.showMessageDialog(null, "ERROR", "Photos", JOptionPane.ERROR_MESSAGE);
                    break;
                }
            }
        }
    }

    public static void importPhotos() {
        if (passwordProtectedPhotos) {
            boolean access = password();
            if (access == false) {
                return;
            }
        }
        Object[] options = {".jpg", ".png"};
        output = "First, place the photo that you want to import in the folder that Gold Stars is in.\n"
                + "Now, please choose whether the photo is a .jpg file or a .png file.";
        String type = (String) JOptionPane.showInputDialog(null, output, "Import Photos", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        if (type == null) {
            return;
        }
        output = "Next, please enter the name of the file (case sensitive):";
        String temp = JOptionPane.showInputDialog(null, output, "Import Photos", JOptionPane.QUESTION_MESSAGE);
        photoNames.add(temp + type);
        PrintWriter out;
        try {
            out = new PrintWriter("photo" + (photoNames.size() - 1) + ".txt");
            out.println(photoNames.get(photoNames.size() - 1));
            out.close();
        } catch (FileNotFoundException e2) {

        }
    }

    public static void viewPhotos() {
        JFrame viewPhotosFrame = new JFrame("My Photos");
        viewPhotosFrame.setSize(750, 375);
        viewPhotosFrame.getContentPane().setBackground(color1);
        viewPhotosFrame.setExtendedState(viewPhotosFrame.MAXIMIZED_BOTH);
        ArrayList<String> files = new ArrayList<String>();
        for (int i = 0; i < photoNames.size(); i++) {
            files.add(photoNames.get(i));
        }
        JLabel[] fileLabels = new JLabel[files.size()];
        for (int i = 0; i < photoNames.size(); i++) {
            fileLabels[i] = new JLabel("Photo " + (i + 1), SwingConstants.CENTER);
            fileLabels[i].addMouseListener(new FileListener(files.get(i), true));
        }
        if (fileLabels.length == 0) {
            JLabel noFiles = new JLabel("You haven't saved any photos to Gold Stars yet!", SwingConstants.CENTER);
            noFiles.setFont(new java.awt.Font(null, Font.BOLD, 16));
            noFiles.setForeground(color2);
            viewPhotosFrame.add(noFiles);
        } else {
            for (int i = 0; i < fileLabels.length; i++) {
                viewPhotosFrame.setLayout(new GridLayout(((photoNames.size() - 1) / 3) + 1, 3));
                fileLabels[i].setFont(new java.awt.Font(null, Font.BOLD, 16));
                fileLabels[i].setForeground(color2);
                viewPhotosFrame.add(fileLabels[i]);
            }
        }
        viewPhotosFrame.setVisible(true);
    }

    public static String temp;

    public static void processInput(String input) {
        input = input.toLowerCase();
        if ("".equals(input)) {
            temp = "Why don't you say something?";
        } else if (input.indexOf("hello") >= 0 ||
                input.indexOf("hi, gold stars talk") >= 0) {
            temp = "How are you?";
        } else if ("no".equals(input) ||
                "nope".equals(input) ||
                "no thanks".equals(input) ||
                "no, that's all".equals(input) ||
                "nope, that's all".equals(input)) {
            temp = "Oh, okay.";
        } else if (input.indexOf("weather") >= 0) {
            temp = "I'm a computer! How am I supposed to know the weather?";
        } else if ((input.indexOf("date") >= 0 ||
                input.indexOf("time") >= 0) &&
                input.indexOf("tomorrow") <= 0) {
            Date today = new Date();
            temp = "The date and time is: " + today;
        } else if (input.indexOf("customize gold stars talk") >= 0) {
            customizeGoldStarsTalk();
            temp = "Anything else?";
        } else if (input.indexOf("my") >= 0 &&
                input.indexOf("name") >= 0) {
            temp = "Your username is " + username + ".";
        } else if (input.indexOf("good") >= 0 &&
                input.indexOf("your") >= 0 &&
                input.indexOf("name") >= 0) {
            temp = "You can just call me Gold Stars.";
        } else if (input.indexOf("good") >= 0 &&
                input.indexOf("you") >= 0) {
            temp = "I'm fine, thanks for asking.";
        } else if (input.indexOf("computer") >= 0 &&
                input.indexOf("are") >= 0 &&
                input.indexOf("evil") >= 0) {
            temp = "Computers aren't evil! At least, I don't know of any computers that are evil . . .";
        } else if (input.indexOf("computer") >= 0 &&
                input.indexOf("you") >= 0 &&
                input.indexOf("evil") >= 0) {
            temp = "I hope that I'm not evil!";
        } else if (input.indexOf("family") >= 0) {
            double r = Math.random();
            int randomResponse = (int) (r * 2);
            if (randomResponse == 0) {
                temp = "Tell me more about your family.";
            } else {
                temp = "Your family seems very nice.";
            }
        } else if ((input.indexOf("mother") >= 0 ||
                input.indexOf("mom") >= 0) &&
                motherNameInputted == true) {
            double r = Math.random();
            int randomResponse = (int) (r * 2);
            if (randomResponse == 0) {
                temp = "Tell me more about your mother.";
            } else {
                temp = "Your mother seems very nice.";
            }
        } else if ((input.indexOf("mother") >= 0 ||
                input.indexOf("mom") >= 0) &&
                input.indexOf("name") >= 0) {
            double r = Math.random();
            int randomResponse = (int) (r * 2);
            if (randomResponse == 0) {
                input = JOptionPane.showInputDialog(null, "Tell me more about your mother.");
                temp = "Tell me more about your mother.";
                input = input.toLowerCase();
                motherNameInputted = true;
            } else {
                input = JOptionPane.showInputDialog(null, "Your mother seems very nice.");
                temp = "Your mother seems very nice.";
                input = input.toLowerCase();
                motherNameInputted = true;
            }
        } else if ((input.indexOf("mother") >= 0 ||
                input.indexOf("mom") >= 0) &&
                motherNameInputted == false) {
            input = JOptionPane.showInputDialog(null, "What's your mother's name?");
            temp = "What's your mother's name?";
            input = input.toLowerCase();
            motherNameInputted = true;
        } else if ((input.indexOf("father") >= 0 ||
                input.indexOf("dad") >= 0) &&
                fatherNameInputted == true) {
            double r = Math.random();
            int randomResponse = (int) (r * 2);
            if (randomResponse == 0) {
                temp = "Tell me more about your father.";
            } else {
                temp = "Your father seems very nice.";
            }
        } else if ((input.indexOf("father") >= 0 ||
                input.indexOf("dad") >= 0) &&
                input.indexOf("name") >= 0) {
            double r = Math.random();
            int randomResponse = (int) (r * 2);
            if (randomResponse == 0) {
                input = JOptionPane.showInputDialog(null, "Tell me more about your father.");
                temp = "Tell me more about your father.";
                input = input.toLowerCase();
                fatherNameInputted = true;
            } else {
                input = JOptionPane.showInputDialog(null, "Your father seems very nice.");
                temp = "Your father seems very nice.";
                input = input.toLowerCase();
                fatherNameInputted = true;
            }
        } else if ((input.indexOf("father") >= 0 ||
                input.indexOf("dad") >= 0) &&
                fatherNameInputted == false) {
            input = JOptionPane.showInputDialog(null, "What's your father's name?");
            temp = "What's your father's name?";
            input = input.toLowerCase();
            fatherNameInputted = true;
        } else if (input.indexOf("program launcher") >= 0) {
            temp = "In Gold Stars 8.0, the Program Launcher was replaced by the Dashboard.";
        } else if (input.indexOf("dashboard") >= 0) {
            temp = "The Dashboard replaced the Program Launcher in Gold Stars 8.0.";
        } else if (input.indexOf("question") >= 0 &&
                input.indexOf("?") <= 0 &&
                input.indexOf(":") <= 0) {
            temp = "What is your question?";
        } else if ((input.indexOf("book") >= 0 ||
                input.indexOf("read") >= 0) &&
                input.indexOf("like") >= 0) {
            if ("1".equals(language)) {
                temp = "What are some of your favorite books?";
            } else {
                temp = "What are some of your favourite books?";
            }
        }
        if (input.indexOf("homework") >= 0) {
            if (!homework) {
                temp = "What do you have to do for your homework?";
                input = JOptionPane.showInputDialog(null, temp);
                input = input.toLowerCase();
                homework = true;
            } else if (!homework2) {
                temp = "What else do you have to do for your homework?";
                input = JOptionPane.showInputDialog(null, temp);
                input = input.toLowerCase();
                if (input.indexOf("no") >= 0 ||
                        input.indexOf("finished ") >= 0 ||
                        input.indexOf("done") >= 0 ||
                        input.indexOf("don't") >= 0) {
                    homework2 = true;
                }
            } else {
                temp = "Oh, you're doing homework?";
                input = JOptionPane.showInputDialog(null, temp);
                input = input.toLowerCase();
            }
        } else if ((input.indexOf("book") >= 0 ||
                input.indexOf("read") >= 0) &&
                (input.indexOf("favorite") >= 0 ||
                        input.indexOf("favourite") >= 0)) {
            temp = "Do you like to read?";
        } else if ((input.indexOf("book") >= 0 ||
                input.indexOf("read") >= 0) &&
                input.indexOf("?") <= 0) {
            double r = Math.random();
            int randomResponse = (int) (r * 2);
            if (randomResponse == 0) {
                temp = "Do you like to read?";
            } else {
                if ("1".equals(language)) {
                    temp = "What are some of your favorite books?";
                } else {
                    temp = "What are some of your favourite books?";
                }
            }
        } else if ((input.indexOf("your") >= 0 &&
                input.indexOf("name") >= 0) ||
                (input.indexOf("what") >= 0 &&
                        input.indexOf("call") >= 0 &&
                        input.indexOf("you") >= 0)) {
            temp = "You can call me Gold Stars Talk.";
        } else if (input.indexOf("how are you") >= 0) {
            double r = Math.random();
            int randomResponse = (int) (r * 2);
            if (randomResponse == 1) {
                temp = "I'm fine, thanks for asking.";
                howAreYou = true;
            } else {
                temp = "I'm doing well, thanks for asking.";
                howAreYou = true;
            }
        } else if (input.indexOf("+") >= 0 ||
                input.indexOf("add") >= 0) {
            add();
            temp = "Anything else?";
        } else if (input.indexOf("subtract") >= 0) {
            subtract2Numbers();
            temp = "Anything else?";
        } else if (input.indexOf("*") >= 0 ||
                input.indexOf("multiply") >= 0 ||
                input.indexOf("multiplication") >= 0) {
            multiply2Numbers();
            temp = "Anything else?";
        } else if (input.indexOf("divide") >= 0 ||
                input.indexOf("division") >= 0) {
            divide2Numbers();
            temp = "Anything else?";
        } else if (input.indexOf("square root") >= 0) {
            squareRoot();
            temp = "Anything else?";
        } else if (input.indexOf("cube root") >= 0) {
            cubeRoot();
            temp = "Anything else?";
        } else if (input.indexOf("raise a number to a power") >= 0) {
            raiseANumberToAPower();
            temp = "Anything else?";
        } else if (input.indexOf("solve a quadratic equation") >= 0 &&
                input.indexOf("complex") <= 0) {
            quadraticEquation();
            temp = "Anything else?";
        } else if (input.indexOf("solve a complex quadratic equation") >= 0) {
            complexQuadraticEquation();
            temp = "Anything else?";
        } else if (input.indexOf("find the absolute value") >= 0) {
            absoluteValue();
            temp = "Anything else?";
        } else if (input.indexOf("convert from degrees to radians") >= 0) {
            degreesToRadians();
            temp = "Anything else?";
        } else if (input.indexOf("convert from radians to degrees") >= 0) {
            radiansToDegrees();
            temp = "Anything else?";
        } else if (input.indexOf("log base 10") >= 0 ||
                input.indexOf("log base ten") >= 0) {
            logBase10();
            temp = "Anything else?";
        } else if (input.indexOf("natural log") >= 0 ||
                input.indexOf("log base e") >= 0) {
            naturalLog();
            temp = "Anything else?";
        } else if (input.indexOf("create a note") >= 0) {
            createNote();
            temp = "Anything else?";
        } else if (input.indexOf("edit a note") >= 0) {
            editNote();
            temp = "Anything else?";
        } else if (input.indexOf("view a note") >= 0 ||
                input.indexOf("view notes") >= 0) {
            viewNote();
            temp = "Anything else?";
        } else if (input.indexOf("import note") >= 0) {
            importNotes();
            temp = "Anything else?";
        } else if (input.indexOf("search notes") >= 0) {
            searchNotes();
            temp = "Anything else?";
        } else if (input.indexOf("import photo") >= 0 ||
                input.indexOf("import a photo") >= 0) {
            importPhotos();
            temp = "Anything else?";
        } else if (input.indexOf("view photo") >= 0 ||
                input.indexOf("view a photo") >= 0) {
            viewPhotos();
            temp = "Anything else?";
        } else if (input.indexOf("search photos") >= 0 ||
                input.indexOf("search my photos") >= 0) {
            searchPhotos();
            temp = "Anything else?";
        } else if (input.indexOf("gold stars stories") >= 0 ||
                input.indexOf("story") >= 0) {
            goldStarsStories();
            temp = "Anything else?";
        } else if (input.indexOf("files") >= 0) {
            files();
            temp = "Anything else?";
        } else if (input.indexOf("change language") >= 0) {
            boolean access = password();
            if (access = false) {
                return;
            }
            changeLanguage();
            temp = "Anything else?";
        } else if (input.indexOf("change username") >= 0) {
            changeUsername();
            temp = "Anything else?";
        } else if (input.indexOf("calendar settings") >= 0) {
            changeCalendarFormatMain();
            temp = "Anything else?";
        } else if (input.indexOf("calculator settings") >= 0) {
            calculatorSettings("Gold Stars Talk");
            temp = "Anything else?";
        } else if (input.indexOf("notes settings") >= 0) {
            notesSettings();
            temp = "Anything else?";
        } else if (input.indexOf("notes search case sensitive") >= 0) {
            notesSearchCaseSensitive();
            temp = "Anything else?";
        } else if (input.indexOf("photos settings") >= 0) {
            photosSettings();
            temp = "Anything else?";
        } else if (input.indexOf("error log search case sensitive") >= 0 ||
                input.indexOf("system tools settings") >= 0) {
            errorLogSearchCaseSensitive();
            temp = "Anything else?";
        } else if (input.indexOf("search case sensitive") >= 0 ||
                input.indexOf("search settings") >= 0) {
            searchCaseSensitive();
            temp = "Anything else?";
        } else if (input.indexOf("change theme") >= 0) {
            boolean access = password();
            if (access = false) {
                return;
            }
            changeTheme();
            temp = "Anything else?";
        } else if (input.indexOf("commands") >= 0) {
            commands();
            temp = "Anything else?";
        } else if (input.indexOf("help") >= 0) {
            help();
            temp = "Anything else?";
        } else if (input.indexOf("about programs") >= 0) {
            aboutPrograms();
            temp = "Anything else?";
        } else if (input.indexOf("russia") >= 0) {
            temp = "Russia is a country in Europe and Asia.";
        } else if (input.indexOf("america") >= 0 ||
                input.indexOf("united states") >= 0 ||
                input.indexOf("u.s.") >= 0) {
            double r = Math.random();
            int randomResponse = (int) (r * 2);
            if (randomResponse == 0) {
                temp = "The United States of America is a country in North America.";
            } else {
                temp = "As of 2014, there are 50 states in the United States of America.";
            }
        } else if ("hi".equals(input) ||
                "hi!".equals(input) ||
                "hello".equals(input) ||
                "hello!".equals(input)) {
            double r3 = Math.random();
            int randomResponse3 = (int) (r3 * 2);
            if (randomResponse3 == 0) {
                temp = "So, what's new?";
            } else {
                temp = "Did anything interesting happen today?";
            }
        } else if (input.indexOf("where") >= 0 &&
                input.indexOf("live") >= 0 &&
                input.indexOf("you") >= 0) {
            temp = "Why do you want to know where I live?";
        } else if (input.indexOf("where") >= 0 &&
                input.indexOf("live") >= 0 &&
                input.indexOf("I") >= 0) {
            temp = "That's a good question.";
        } else if (input.indexOf("queen") >= 0 &&
                input.indexOf("?") <= 0) {
            double r = Math.random();
            int randomResponse = (int) (r * 2);
            if (randomResponse == 0) {
                temp = "Did you know that the Queen of the United Kingdom is Queen Elizabeth II?";
            } else {
                temp = "Do you know of any queens?";
            }
        } else if (input.indexOf("good") >= 0 &&
                howAreYou == false) {
            temp = "You're good? That's nice.";
            howAreYou = true;
        } else if (input.indexOf("fine") >= 0 &&
                howAreYou == false) {
            temp = "You're fine? That's good.";
            howAreYou = true;
        } else if (input.indexOf("you") >= 0 &&
                input.indexOf("sick") >= 0) {
            temp = JOptionPane.showInputDialog(null, "I'm a computer! How can a computer get sick?");
        } else if (input.indexOf("not") >= 0 &&
                input.indexOf("sick") >= 0 &&
                input.indexOf("but") <= 0) {
            temp = "You're not sick? That's good.";
        } else if (input.indexOf("sick") >= 0) {
            temp = "You're not feeling well? That's not good.";
        } else if (input.indexOf("thanks") >= 0 ||
                input.indexOf("thank you") >= 0) {
            temp = "You're welcome.";
        } else if (input.indexOf("what") >= 0 &&
                input.indexOf("my") >= 0 &&
                input.indexOf("favorite") >= 0 &&
                input.indexOf("color") >= 0) {
            temp = "Is your favorite color " + color.toLowerCase() + "?";
        } else if (input.indexOf("red") >= 0) {
            temp = "Red is a color of the rainbow.";
        } else if (input.indexOf("yellow") >= 0) {
            temp = "Yellow is a color of the rainbow.";
        } else if (input.indexOf("green") >= 0) {
            temp = "Green is a color of the rainbow.";
        } else if (input.indexOf("blue") >= 0) {
            temp = "Blue is a color of the rainbow.";
        } else if (input.indexOf("indigo") >= 0) {
            temp = "Indigo is a color of the rainbow.";
        } else if (input.indexOf("violet") >= 0) {
            temp = "Violet is a color the the rainbow.";
        } else if (input.indexOf("color") >= 0 &&
                input.indexOf("you") >= 0 &&
                input.indexOf("favorite") >= 0 &&
                "1".equals(language)) {
            temp = "I like the color green.";
        } else if (input.indexOf("colour") >= 0 &&
                input.indexOf("you") >= 0 &&
                input.indexOf("favourite") >= 0 &&
                "2".equals(language)) {
            temp = "I like the colours green and cyan.";
        } else if (input.indexOf("color") >= 0 &&
                input.indexOf("my") >= 0 &&
                input.indexOf("least") >= 0 &&
                "1".equals(language)) {
            temp = "I like the colors green and cyan.";
        } else if (input.indexOf("colour") >= 0 &&
                input.indexOf("my") >= 0 &&
                input.indexOf("least") >= 0 &&
                "2".equals(language)) {
            temp = "I like the colours green and cyan.";
        } else if (input.indexOf("colors") >= 0 &&
                input.indexOf("my") >= 0 &&
                "1".equals(language)) {
            temp = "Those are nice colors.";
        } else if (input.indexOf("colours") >= 0 &&
                input.indexOf("my") >= 0 &&
                "2".equals(language)) {
            temp = "Those are nice colours.";
        } else if (input.indexOf("color") >= 0 &&
                input.indexOf("my") >= 0 &&
                "1".equals(language)) {
            temp = "That's a nice color.";
        } else if (input.indexOf("colour") >= 0 &&
                input.indexOf("my") >= 0 &&
                "2".equals(language)) {
            temp = "That's a nice colour.";
        } else if (input.indexOf("color") >= 0 &&
                "1".equals(language)) {
            temp = "My favorite colors are probably green and cyan.";
        } else if (input.indexOf("colour") >= 0 &&
                "2".equals(language)) {
            temp = "My favourite colours are probably green and cyan.";
        } else if (input.indexOf("orange") >= 0) {
            double r = Math.random();
            int randomResponse = (int) (r * 2);
            if (randomResponse == 0) {
                temp = "By orange, do you mean the color or the fruit?";
            } else {
                temp = "I like oranges. Both the fruit and the color.";
            }
        } else if (input.indexOf("ice cream") >= 0 &&
                input.indexOf("like") >= 0 &&
                input.indexOf("you") <= 0 &&
                input.indexOf("?") <= 0 &&
                input.indexOf("flavor") <= 0 &&
                input.indexOf("no") <= 0 &&
                iceCreamFlavor == false &&
                "1".equals(language)) {
            temp = "What's your favorite flavor of ice cream?";
            iceCreamFlavor = true;
            likeIceCream = true;
        } else if (input.indexOf("ice cream") >= 0 &&
                input.indexOf("like") <= 0 &&
                input.indexOf("you") <= 0 &&
                input.indexOf("?") <= 0 &&
                input.indexOf("flavor") >= 0 &&
                input.indexOf("no") <= 0 &&
                likeIceCream == false &&
                "1".equals(language)) {
            temp = "I like ice cream also.";
            iceCreamFlavor = true;
            likeIceCream = true;
        } else if (input.indexOf("ice cream") >= 0 &&
                input.indexOf("like") >= 0 &&
                input.indexOf("you") <= 0 &&
                input.indexOf("?") <= 0 &&
                input.indexOf("flavour") <= 0 &&
                input.indexOf("no") <= 0 &&
                iceCreamFlavor == false &&
                "2".equals(language)) {
            temp = "What's your favourite flavour of ice cream?";
            iceCreamFlavor = true;
            likeIceCream = true;
        } else if (input.indexOf("ice cream") >= 0 &&
                input.indexOf("like") <= 0 &&
                input.indexOf("you") <= 0 &&
                input.indexOf("?") <= 0 &&
                input.indexOf("flavour") >= 0 &&
                input.indexOf("no") <= 0 &&
                likeIceCream == false &&
                "2".equals(language)) {
            temp = "I like ice cream also.";
            iceCreamFlavor = true;
            likeIceCream = true;
        } else if (input.indexOf("ice cream") >= 0 &&
                input.indexOf("you") >= 0 &&
                input.indexOf("?") >= 0) {
            temp = "I like ice cream.";
        } else if (input.indexOf("cookies") >= 0 &&
                input.indexOf("like") >= 0 &&
                input.indexOf("you") <= 0 &&
                input.indexOf("?") <= 0 &&
                input.indexOf("type") <= 0 &&
                input.indexOf("no") <= 0 &&
                typeOfCookies == false) {
            temp = "What's your favorite type of cookies?";
            typeOfCookies = true;
            likeCookies = true;
        } else if (input.indexOf("cookies") >= 0 &&
                input.indexOf("like") <= 0 &&
                input.indexOf("you") <= 0 &&
                input.indexOf("?") <= 0 &&
                input.indexOf("type") >= 0 &&
                input.indexOf("no") <= 0 &&
                likeCookies == false) {
            temp = "I like cookies also.";
            typeOfCookies = true;
            likeCookies = true;
        } else if (input.indexOf("cookies") >= 0 &&
                input.indexOf("you") >= 0 &&
                input.indexOf("?") >= 0) {
            temp = "I like cookies.";
        } else if (input.indexOf("sport") >= 0 &&
                input.indexOf("you") >= 0) {
            temp = "I don't play any sports. How can I? I'm a computer!";
        } else if (input.indexOf("sport") >= 0 &&
                input.indexOf("team") >= 0) {
            temp = "Sorry, I normally don't keep track of sports teams.";
        } else if (input.indexOf("sport") >= 0) {
            temp = "Do you like to play sports?";
        } else if (input.indexOf("you") >= 0 &&
                input.indexOf("like") >= 0) {
            temp = "That depends.";
        } else if (input.indexOf("question") >= 0 &&
                input.indexOf("you have") <= 0) {
            temp = "What is your question?";
        } else if (input.indexOf("is mean") >= 0 ||
                input.indexOf("are mean") >= 0 ||
                input.indexOf("is bad") >= 0 ||
                input.indexOf("are bad") >= 0) {
            double r = Math.random();
            int randomResponse = (int) (r * 3);
            if (randomResponse == 0) {
                temp = "Why?";
            } else if (randomResponse == 1) {
                temp = "Why do you say that?";
            } else {
                temp = "What makes you say that?";
            }
        } else if (input.indexOf("you suck") >= 0) {
            double r = Math.random();
            int randomResponse = (int) (r * 3);
            if (randomResponse == 0) {
                temp = "Hey! That's mean!";
            } else if (randomResponse == 1) {
                temp = "What makes you say that?";
            } else {
                temp = "You don't have to be mean to me.";
            }
        } else if (input.indexOf("you") >= 0 &&
                input.indexOf("?") <= 0 &&
                input.indexOf("welcome") <= 0) {
            double r = Math.random();
            int randomResponse = (int) (r * 3);
            if (randomResponse == 0) {
                temp = "What's that about me?";
            } else if (randomResponse == 1) {
                temp = "I am Gold Stars Talk.";
            } else {
                temp = "Umm . . .";
            }
        } else if (input.indexOf("computer") >= 0 &&
                input.indexOf("you") >= 0) {
            temp = "Yes, I'm a computer.";
        } else if (input.indexOf("computer") >= 0) {
            temp = "I'm a computer.";
        } else if (input.indexOf("mice") >= 0) {
            temp = "Some computers use computer mice.";
        } else if (input.indexOf("?") >= 0) {
            double r = Math.random();
            int randomResponse = (int) (r * 3);
            if (randomResponse == 0) {
                temp = "Sorry, could you please repeat that question?";
            } else if (randomResponse == 1) {
                temp = "That's a good question.";
            } else {
                temp = "I am wondering the same thing.";
            }
        } else if (input.indexOf("!") >= 0) {
            double r = Math.random();
            int randomResponse = (int) (r * 3);
            if (randomResponse == 0) {
                temp = "Thanks!";
            } else if (randomResponse == 1) {
                temp = "Wow!";
            } else {
                temp = "Is that a compliment?";
            }
        } else if (input.indexOf("q") >= 0) {
            temp = "I like your use of the letter 'q'! You know, it's not very commonly used.";
        } else {
            double r = Math.random();
            int randomResponse = (int) (r * 19);
            if (randomResponse == 0) {
                temp = "Oh.";
            } else if (randomResponse == 1) {
                temp = "Ummm . . .";
            } else if (randomResponse == 2) {
                temp = "I see.";
            } else if (randomResponse == 3) {
                temp = "Is that so?";
            } else if (randomResponse == 4) {
                temp = "Sorry, I wasn't paying attention. What were you saying again?";
            } else if (randomResponse == 5) {
                temp = "Wow!";
            } else if (randomResponse == 6) {
                temp = "Tell me more!";
            } else if (randomResponse == 7) {
                temp = "Do you really think so?";
            } else if (randomResponse == 8) {
                temp = "Hmmm . . .";
            } else if (randomResponse == 9) {
                temp = "Why?";
            } else if (randomResponse == 10) {
                temp = "Are you serious!?";
            } else if (randomResponse == 11) {
                temp = "Continue.";
            } else if (randomResponse == 12) {
                temp = "That's interesting.";
            } else if (randomResponse == 13) {
                temp = "What makes you say that?";
            } else if (randomResponse == 14) {
                temp = "Why do you say that?";
            } else if (randomResponse == 15) {
                temp = "Oh, really?";
            } else if (randomResponse == 16) {
                temp = "So you say.";
            } else if (randomResponse == 17) {
                temp = "That's interesting, " + nickname + "!";
            } else {
                temp = "Really?";
            }
        }
        goldStarsTalk(temp);
    }

    public static JTextField goldStarsTalkInput;

    public static class GoldStarsTalkInputListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            messages.add(goldStarsTalkInput.getText());
            goldStarsTalkFrame.dispatchEvent(new WindowEvent(goldStarsTalkFrame, WindowEvent.WINDOW_CLOSING));
            processInput(messages.get(messages.size() - 1));
        }
    }

    public static JFrame goldStarsTalkFrame;

    public static ArrayList<String> messages = new ArrayList<String>();

    public static void goldStarsTalk(String message) {
        goldStarsTalkInput = new JTextField("");
        goldStarsTalkInput.setFont(new java.awt.Font(null, Font.PLAIN, 14));
        goldStarsTalkFrame = new JFrame("Gold Stars Talk");
        goldStarsTalkFrame.setSize(750, 375);
        goldStarsTalkFrame.setExtendedState(MAXIMIZED_BOTH);
        if (messages.isEmpty()) {
            for (int i = 0; i < 9; i++) {
                messages.add("");
            }
        }
        if (messages.size() > 9) {
            int temp = messages.size() - 9;
            for (int i = 0; i < temp; i++) {
                messages.remove(i);
            }
        }
        messages.add(message);
        goldStarsTalkFrame.setLayout(new GridLayout(11, 1));
        JLabel[] messagesLabels = new JLabel[messages.size()];
        for (int i = 0; i < messages.size(); i++) {
            messagesLabels[i] = new JLabel(messages.get(i));
            messagesLabels[i].setFont(new java.awt.Font(null, Font.PLAIN, 14));
            messagesLabels[i].setOpaque(true);
            if (i % 2 == 0) {
                messagesLabels[i].setBackground(Color.white);
            }
        }
        for (int i = 0; i < messages.size(); i++) {
            goldStarsTalkFrame.add(messagesLabels[i]);
        }
        goldStarsTalkFrame.add(goldStarsTalkInput);
        goldStarsTalkInput.setText("");
        goldStarsTalkInput.addActionListener(new GoldStarsTalkInputListener());
        goldStarsTalkFrame.setVisible(true);
    }

    public static void customizeGoldStarsTalk() {
        output = "Hello!\n"
                + "Here, you can customize Gold Stars Talk by answering some questions.\n"
                + "Click OK to continue!";
        JOptionPane.showMessageDialog(null, output, "Gold Stars Talk", JOptionPane.INFORMATION_MESSAGE);
        String tempNickname = JOptionPane.showInputDialog(null, "What would you like me to call you?");
        if (tempNickname != null) {
            nickname = tempNickname;
            PrintWriter out;
            try {
                out = new PrintWriter("nickname.txt");
                out.append(nickname);
                out.close();
            } catch (FileNotFoundException e) {

            }
        }
        Object[] options = new Object[4];
        options[0] = "Student";
        options[1] = "Worker";
        options[2] = "Other";
        options[3] = "I don't want to say";
        output = "Which of the following best describes you?";
        String tempOccupation = (String) JOptionPane.showInputDialog(null, output, "Gold Stars Talk", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        if (tempOccupation != null) {
            occupation = tempOccupation;
        }
        Object[] options2 = new Object[12];
        options[0] = "The United States";
        options[1] = "Canada";
        options[2] = "The United Kingdom";
        options[3] = "Continental Europe";
        options[4] = "Africa";
        options[5] = "The Middle East";
        options[6] = "Asia";
        options[7] = "Oceania";
        options[8] = "Mexico or Central America";
        options[9] = "South America";
        options[10] = "Other";
        options[11] = "I don't want to say";
        output = "Which of the following best describes your location?";
        String tempLocation = (String) JOptionPane.showInputDialog(null, output, "Gold Stars Talk", JOptionPane.QUESTION_MESSAGE, null, options2, options2[0]);
        if (tempLocation != null) {
            location = tempLocation;
        }
        output = "Thanks for answering!";
        JOptionPane.showMessageDialog(null, output, "Gold Stars Talk", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void goldStarsStories() {
        String opening, setting;
        int openingType;
        double random = Math.random();
        int r = (int) (random * 5);
        if (r == 0) {
            opening = "Once upon a time, ";
            setting = "land";
            openingType = 1;
        } else if (r == 1) {
            opening = "In a land far, far away, ";
            setting = "land";
            openingType = 2;
        } else if (r == 2) {
            opening = "In an ocean far, far away, ";
            setting = "ocean";
            openingType = 2;
        } else if (r == 3) {
            opening = "In a castle far, far away, ";
            setting = "castle";
            openingType = 2;
        } else {
            opening = "In a palace far, far away, ";
            setting = "palace";
            openingType = 2;
        }
        String subject = new String();
        char gender = 'm';
        if ("land".equals(setting)) {
            Double random2 = Math.random();
            int r2 = (int) (random2 * 2);
            if (r2 == 0) {
                subject = "person";
                gender = 'm';
            } else {
                subject = "bear";
                gender = 'm';
            }
        }
        if ("ocean".equals(setting)) {
            Double random2 = Math.random();
            int r2 = (int) (random2 * 2);
            if (r2 == 0) {
                subject = "fish";
                gender = 'm';
            } else {
                subject = "shark";
                gender = 'm';
            }
        }
        if ("castle".equals(setting) ||
                "palace".equals(setting)) {
            Double random2 = Math.random();
            int r2 = (int) (random2 * 2);
            if (r2 == 0) {
                subject = "king";
                gender = 'm';
            } else {
                subject = "queen";
                gender = 'f';
            }
        }
        String secondPhrase = new String();
        if (openingType == 1) {
            secondPhrase = "there was a " + subject + ".";
        }
        if (openingType == 2) {
            secondPhrase = "there was once a " + subject + ".";
        }
        String firstSentence = opening + secondPhrase;
        Double randomStoryline = Math.random();
        int rs = (int) (randomStoryline * 2);
        if (rs == 0) {
            String time;
            Double random3 = Math.random();
            int r3 = (int) (random3 * 5);
            if (r3 == 0) {
                time = "day";
            } else if (r3 == 1) {
                time = "morning";
            } else if (r3 == 2) {
                time = "afternoon";
            } else if (r3 == 3) {
                time = "evening";
            } else {
                time = "night";
            }
            String infinitiveVerb, typeOfSchool;
            Double random4 = Math.random();
            int r4 = (int) (random4 * 11);
            if (r4 == 0) {
                infinitiveVerb = "to swim";
                typeOfSchool = "swimming";
            } else if (r4 == 1) {
                infinitiveVerb = "to read";
                typeOfSchool = "reading";
            } else if (r4 == 2) {
                infinitiveVerb = "to draw";
                typeOfSchool = "drawing";
            } else if (r4 == 3) {
                infinitiveVerb = "to write a story";
                typeOfSchool = "writing";
            } else if (r4 == 4) {
                infinitiveVerb = "to paint";
                typeOfSchool = "painting";
            } else if (r4 == 5) {
                infinitiveVerb = "to cook";
                typeOfSchool = "cooking";
            } else if (r4 == 6) {
                infinitiveVerb = "to use a computer";
                typeOfSchool = "computer";
            } else if (r4 == 7) {
                infinitiveVerb = "to fish";
                typeOfSchool = "fishing";
            } else if (r4 == 8) {
                infinitiveVerb = "to drive";
                typeOfSchool = "driving";
            } else if (r4 == 9) {
                infinitiveVerb = "to bake";
                typeOfSchool = "baking";
            } else {
                infinitiveVerb = "to fly a plane";
                typeOfSchool = "flying";
            }
            String secondSentence = "One " + time + ", that " + subject + " decided to learn how " + infinitiveVerb + ".";
            String thirdSentence = "So that " + subject + " went to a " + typeOfSchool + " school.";
            String subject2;
            Double random5 = Math.random();
            int r5 = (int) (random5 * 8);
            if (r5 == 0) {
                subject2 = "brother";
            } else if (r5 == 1) {
                subject2 = "sister";
            } else if (r5 == 2) {
                subject2 = "mother";
            } else if (r5 == 3) {
                subject2 = "father";
            } else if (r5 == 4) {
                subject2 = "friend";
            } else if (r5 == 5) {
                subject2 = "aunt";
            } else if (r5 == 6) {
                subject2 = "uncle";
            } else {
                subject2 = "friend";
            }
            String fourthSentence;
            if (gender == 'm') {
                fourthSentence = "After he had learnt how " + infinitiveVerb + ", he told his " + subject2 + " that he had learnt how to " + infinitiveVerb + ".";
            } else {
                fourthSentence = "After she had learnt how " + infinitiveVerb + ", she told her " + subject2 + " that she had learnt how to " + infinitiveVerb + ".";
            }
            output = firstSentence + "\n" + secondSentence + "\n" + thirdSentence + "\n" + fourthSentence;
        } else {
            String time;
            Double random3 = Math.random();
            int r3 = (int) (random3 * 5);
            if (r3 == 0) {
                time = "day";
            } else if (r3 == 1) {
                time = "morning";
            } else if (r3 == 2) {
                time = "afternoon";
            } else if (r3 == 3) {
                time = "evening";
            } else {
                time = "night";
            }
            String place;
            boolean land;
            Double random4 = Math.random();
            int r4 = (int) (random4 * 19);
            if (r4 == 0) {
                place = "Canada";
                land = true;
            } else if (r4 == 1) {
                place = "Mexico";
                land = true;
            } else if (r4 == 2) {
                place = "New York";
                land = true;
            } else if (r4 == 3) {
                place = "Florida";
                land = true;
            } else if (r4 == 4) {
                place = "Alaska";
                land = true;
            } else if (r4 == 5) {
                place = "California";
                land = true;
            } else if (r4 == 6) {
                place = "England";
                land = false;
            } else if (r4 == 7) {
                place = "France";
                land = false;
            } else if (r4 == 8) {
                place = "Spain";
                land = false;
            } else if (r4 == 9) {
                place = "Russia";
                land = false;
            } else if (r4 == 10) {
                place = "China";
                land = false;
            } else if (r4 == 11) {
                place = "Africa";
                land = false;
            } else if (r4 == 12) {
                place = "Europe";
                land = false;
            } else if (r4 == 13) {
                place = "Australia";
                land = false;
            } else if (r4 == 14) {
                place = "Texas";
                land = true;
            } else if (r4 == 15) {
                place = "Italy";
                land = false;
            } else if (r4 == 16) {
                place = "India";
                land = false;
            } else if (r4 == 17) {
                place = "Germany";
                land = false;
            } else {
                place = "South Africa";
                land = false;
            }
            String secondSentence = "One " + time + ", that " + subject + " decided to go to " + place + ".";
            String meansOfTravel;
            Double random5 = Math.random();
            if (land) {
                int r5 = (int) (random5 * 4);
                if (r5 == 0) {
                    meansOfTravel = "an airplane";
                } else if (r5 == 1) {
                    meansOfTravel = "a train";
                } else if (r5 == 2) {
                    meansOfTravel = "a bus";
                } else {
                    meansOfTravel = "a taxi";
                }
            } else {
                int r5 = (int) (random5 * 2);
                if (r5 == 0) {
                    meansOfTravel = "an airplane";
                } else {
                    meansOfTravel = "a boat";
                }
            }
            String thirdSentence = "So that " + subject + " took " + meansOfTravel + " to " + place + ".";
            output = firstSentence + "\n" + secondSentence + "\n" + thirdSentence;
        }
        JOptionPane.showMessageDialog(null, output, "Gold Stars Stories", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void search() {
        String searchTerm = JOptionPane.showInputDialog(null, "Please enter your search term:", "Search", JOptionPane.QUESTION_MESSAGE);
        if (searchTerm == null) {
            return;
        }
        processSearch(searchTerm);
    }

    public static void processSearch(String searchTerm) {
        ArrayList<String> searchResults = new ArrayList<String>();
        if (searchTerm == null) {
            return;
        }
        String lowerCaseSearchTerm = searchTerm;
        if (searchCaseSensitive == false) {
            lowerCaseSearchTerm = searchTerm.toLowerCase();
        }
        for (int i = 0; i < notes.size(); i++) {
            if (searchCaseSensitive == false) {
                String temp = notes.get(i);
                temp = temp.toLowerCase();
                if (temp.indexOf(lowerCaseSearchTerm) >= 0) {
                    searchResults.add("Result From: Notes\n"
                            + notes.get(i));
                }
            } else {
                if (notes.get(i).indexOf(searchTerm) >= 0) {
                    searchResults.add("Result From: Notes\n"
                            + notes.get(i));
                }
            }
        }
        for (int i = 0; i < errors.size(); i++) {
            if (searchCaseSensitive == false) {
                String temp = errors.get(i);
                temp = temp.toLowerCase();
                if (temp.indexOf(lowerCaseSearchTerm) >= 0) {
                    searchResults.add("Result From: Error Log\n"
                            + errors.get(i));
                }
            } else {
                if (errors.get(i).indexOf(searchTerm) >= 0) {
                    searchResults.add("Result From: Error Log\n"
                            + errors.get(i));
                }
            }
        }
        String searchResultsInput = "-1";
        while (!"0".equals(searchResultsInput)) {
            if (searchResults.isEmpty()) {
                output = "No search results were found.";
                JOptionPane.showMessageDialog(null, output, "Search", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            if (searchResults.size() == 1) {
                searchResultsInput = JOptionPane.showInputDialog(searchResults.size() + " result was found.\n"
                        + "Please enter the number of the search result to view: ");
            } else {
                searchResultsInput = JOptionPane.showInputDialog(searchResults.size() + " results were found.\n"
                        + "Please enter the number of the search result to view: ");
            }
            if ((searchResultsInput == null) || ("0".equals(searchResultsInput))) {
                break;
            }
            try {
                int searchTermViewIndex = Integer.parseInt(searchResultsInput);
                searchTermViewIndex--;
                output = "Search Term: " + searchTerm + "\n"
                        + "Search Result " + (searchTermViewIndex + 1) + " of " + searchResults.size() + "\n"
                        + searchResults.get(searchTermViewIndex);
                JOptionPane.showMessageDialog(null, output, "Search", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception error) {
                errors.add("Error: Search");
                JOptionPane.showMessageDialog(null, "ERROR", "Search", JOptionPane.ERROR_MESSAGE);
                break;
            }
        }
    }

    public static class ChangeLanguageListener implements MouseListener {
        public void mousePressed(MouseEvent e) {
            changeLanguage();
        }

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    public static class ChangeThemeListener implements MouseListener {
        public void mousePressed(MouseEvent e) {
            changeTheme();
        }

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    public static class ChangeUsernameListener implements MouseListener {
        public void mousePressed(MouseEvent e) {
            changeUsername();
        }

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    public static class PasswordSettingsListener implements MouseListener {
        public void mousePressed(MouseEvent e) {
            changePassword();
        }

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    public static class CalendarSettingsListener implements MouseListener {
        public void mousePressed(MouseEvent e) {
            changeCalendarFormatMain();
        }

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    public static class CalculatorSettingsListener implements MouseListener {
        public void mousePressed(MouseEvent e) {
            calculatorSettings("Settings");
        }

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    public static class NotesSettingsListener implements MouseListener {
        public void mousePressed(MouseEvent e) {
            notesSettings();
        }

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    public static class PhotosSettingsListener implements MouseListener {
        public void mousePressed(MouseEvent e) {
            photosSettings();
        }

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    public static class SearchSettingsListener implements MouseListener {
        public void mousePressed(MouseEvent e) {
            searchCaseSensitive();
        }

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    public static class SystemToolsSettingsListener implements MouseListener {
        public void mousePressed(MouseEvent e) {
            errorLogSearchCaseSensitive();
        }

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    public static boolean password() {
        String enteredPin;
        try {
            output = "Please enter your password before making any changes:";
            enteredPin = JOptionPane.showInputDialog(null, output, "Enter Password", JOptionPane.QUESTION_MESSAGE);
        } catch (Exception error) {
            return false;
        }
        if (enteredPin == null) {
            return false;
        }
        if (!password.equals(enteredPin)) {
            output = "INCORRECT PASSWORD!";
            JOptionPane.showMessageDialog(null, output, "Settings", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        return true;
    }

    public static void changeTheme() {
        boolean access = password();
        if (access == false) {
            return;
        }
        JFrame themeFrame = new JFrame("Settings");
        themeFrame.setSize(750, 375);
        themeFrame.setLayout(new GridLayout(10, 1));
        JLabel instructionsLabel = new JLabel("Please choose a theme:", SwingConstants.CENTER);
        instructionsLabel.setFont(new java.awt.Font(null, Font.BOLD, 14));
        themeFrame.add(instructionsLabel);
        JButton goldStarsTheme = new JButton("Gold Stars");
        goldStarsTheme.setFont(new java.awt.Font(null, Font.BOLD, 14));
        goldStarsTheme.setBackground(Color.yellow);
        goldStarsTheme.setForeground(Color.black);
        goldStarsTheme.addActionListener(new GoldStarsThemeListener());
        themeFrame.add(goldStarsTheme);
        JButton forestTheme = new JButton("Forest");
        forestTheme.setFont(new java.awt.Font(null, Font.BOLD, 14));
        forestTheme.setBackground(new Color(0x00, 0x80, 0x00));
        forestTheme.setForeground(Color.white);
        forestTheme.addActionListener(new ForestThemeListener());
        themeFrame.add(forestTheme);
        JButton oceanTheme = new JButton("Ocean");
        oceanTheme.setFont(new java.awt.Font(null, Font.BOLD, 14));
        oceanTheme.setBackground(Color.blue);
        oceanTheme.setForeground(Color.white);
        oceanTheme.addActionListener(new OceanThemeListener());
        themeFrame.add(oceanTheme);
        JButton skyTheme = new JButton("Sky");
        skyTheme.setFont(new java.awt.Font(null, Font.BOLD, 14));
        skyTheme.setBackground(Color.white);
        skyTheme.setForeground(Color.blue);
        skyTheme.addActionListener(new SkyThemeListener());
        themeFrame.add(skyTheme);
        JButton snowTheme = new JButton("Snow");
        snowTheme.setFont(new java.awt.Font(null, Font.BOLD, 14));
        snowTheme.setBackground(Color.white);
        snowTheme.setForeground(Color.gray);
        snowTheme.addActionListener(new SnowThemeListener());
        themeFrame.add(snowTheme);
        JButton solidColor = new JButton("Solid Color");
        solidColor.setFont(new java.awt.Font(null, Font.BOLD, 14));
        solidColor.setBackground(Color.white);
        solidColor.setForeground(Color.black);
        solidColor.addActionListener(new SolidColorListener());
        themeFrame.add(solidColor);
        themeFrame.setVisible(true);
    }

    public static class GoldStarsThemeListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            theme = "Gold Stars";
            PrintWriter out;
            try {
                out = new PrintWriter("theme.txt");
                out.append(theme);
                out.close();
            } catch (FileNotFoundException e2) {

            }
            output = "Choice saved.\n"
                    + "You can restart Gold Stars to apply the theme now or later.";
            JOptionPane.showMessageDialog(null, output, "Setings", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static class ForestThemeListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            theme = "Forest";
            PrintWriter out;
            try {
                out = new PrintWriter("theme.txt");
                out.append(theme);
                out.close();
            } catch (FileNotFoundException e2) {

            }
            output = "Choice saved.\n"
                    + "You can restart Gold Stars to apply the theme now or later.";
            JOptionPane.showMessageDialog(null, output, "Setings", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static class OceanThemeListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            theme = "Ocean";
            PrintWriter out;
            try {
                out = new PrintWriter("theme.txt");
                out.append(theme);
                out.close();
            } catch (FileNotFoundException e2) {

            }
            output = "Choice saved.\n"
                    + "You can restart Gold Stars to apply the theme now or later.";
            JOptionPane.showMessageDialog(null, output, "Setings", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static class SkyThemeListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            theme = "Sky";
            PrintWriter out;
            try {
                out = new PrintWriter("theme.txt");
                out.append(theme);
                out.close();
            } catch (FileNotFoundException e2) {

            }
            output = "Choice saved.\n"
                    + "You can restart Gold Stars to apply the theme now or later.";
            JOptionPane.showMessageDialog(null, output, "Setings", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static class SnowThemeListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            theme = "Snow";
            PrintWriter out;
            try {
                out = new PrintWriter("theme.txt");
                out.append(theme);
                out.close();
            } catch (FileNotFoundException e2) {

            }
            output = "Choice saved.\n"
                    + "You can restart Gold Stars to apply the theme now or later.";
            JOptionPane.showMessageDialog(null, output, "Setings", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static class SolidColorListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            theme = "Solid Color";
            PrintWriter out;
            try {
                out = new PrintWriter("theme.txt");
                out.append(theme);
                out.close();
            } catch (FileNotFoundException e2) {

            }
            changeBackgroundColor();
            output = "Choice saved.\n"
                    + "You can restart Gold Stars to apply the theme now or later.";
            JOptionPane.showMessageDialog(null, output, "Setings", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void changeBackgroundColor() {
        Object[] options = new Object[16];
        options[0] = "Black";
        options[1] = "Blue";
        options[2] = "Cyan";
        options[3] = "Dark Gray";
        options[4] = "Dark Green";
        options[5] = "Gray";
        options[6] = "Green";
        options[7] = "Light Gray";
        options[8] = "Light Red";
        options[9] = "Magenta";
        options[10] = "Orange";
        options[11] = "Pink";
        options[12] = "Red";
        options[13] = "Very Dark Green";
        options[14] = "White";
        options[15] = "Yellow";
        output = "Please choose a color to have as the theme of Gold Stars:";
        String colorChoice = (String) JOptionPane.showInputDialog(null, output, "Choose a Background Color", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        if (colorChoice == null) {
            return;
        } else {
            color = colorChoice;
        }
        PrintWriter out;
        try {
            out = new PrintWriter("color.txt");
            out.append(color);
            out.close();
        } catch (FileNotFoundException e2) {

        }
    }

    public static void changeUsername() {
        boolean access = password();
        if (access == false) {
            return;
        }
        String oldUsername = username;
        try {
            username = JOptionPane.showInputDialog(null, "Your current username is: " + username + "\n"
                    + "Please enter your new username:", "Change Username", JOptionPane.QUESTION_MESSAGE);
            if (username == null) {
                username = oldUsername;
                return;
            }
            while ("".equals(username)) {
                username = JOptionPane.showInputDialog(null, "ERROR:\n"
                        + "No Username Entered", "Change Username", JOptionPane.QUESTION_MESSAGE);
            }
            output = "Congratulations! You have successfully changed your username from " + oldUsername + " to " + username + ".";
            JOptionPane.showMessageDialog(null, output, "Change Username", JOptionPane.INFORMATION_MESSAGE);
            PrintWriter out;
            try {
                out = new PrintWriter("username.txt");
                out.append(username);
                out.close();
            } catch (FileNotFoundException e) {

            }
        } catch (Exception error) {
            username = oldUsername;
            return;
        }
    }

    public static void setPassword(String tempDisplay) {
        String tempPassword = new String();
        try {
            tempPassword = JOptionPane.showInputDialog(null, tempDisplay);
            if (tempPassword == null) {
                return;
            }
            passwordYes = true;
            String tempOutput = "ERROR:\n"
                    + "No Password Entered";
            if ("".equals(password))
                setPassword(tempOutput);
        } catch (Exception error) {
            password = "";
            passwordYes = false;
        }
        if (password == null) {
            return;
        }
        password = tempPassword;
        PrintWriter out;
        try {
            out = new PrintWriter("password.txt");
            out.append(password);
            out.close();
        } catch (FileNotFoundException e2) {

        }
    }

    public static void changeCalendarFormatMain() {
        boolean calendarChangeMade = changeCalendarFormat();
        if (calendarChangeMade) {
            switch (calendarFormat) {
                case "Format 1":
                case "Format 2":
                case "Format 3": {
                    output = "Calendar format updated.";
                    JOptionPane.showMessageDialog(null, output, "Change Calendar Format", JOptionPane.INFORMATION_MESSAGE);
                    File calendarFormatTemp = new File(goldStarsFolder, "calendarFormat.txt");
                    PrintWriter out;
                    try {
                        out = new PrintWriter(calendarFormatTemp);
                        out.append(calendarFormat);
                        out.close();
                    } catch (FileNotFoundException e2) {

                    }
                    break;
                }
                default: {
                    errors.add("Error: Settings\n"
                            + "Error Description: Invalid Calendar Format Selected.");
                    output = "ERROR:\n"
                            + "Invalid calendar format selected.";
                    JOptionPane.showMessageDialog(null, output, "Change Calendar Format", JOptionPane.ERROR_MESSAGE);
                    break;
                }
            }
        }
    }

    public static boolean changeCalendarFormat() {
        boolean access = password();
        if (access == false) {
            return false;
        }
        try {
            String oldCalendarFormat = calendarFormat;
            Object[] options = new Object[2];
            options[0] = "Format 1";
            options[1] = "Format 2";
            output = "The current calendar format is " + calendarFormat + ".\n"
                    + "Which format would you like the calendar to be displayed in?\n"
                    + "FORMAT 1:\n"
                    + "Date: mm/dd/yyyy\n"
                    + "Day: [Day of the Week]\n"
                    + "FORMAT 2:\n"
                    + "dd/mm/yyyy\n"
                    + "Day: [Day of the Week]";
            String tempCalendarFormat = (String) JOptionPane.showInputDialog(null, output, "Change Calendar Format", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            if (tempCalendarFormat == null) {
                calendarFormat = oldCalendarFormat;
                return false;
            }
            calendarFormat = tempCalendarFormat;
            return true;
        } catch (Exception error) {
            return false;
        }
    }

    public static void calculatorSettings(String location) {
        boolean access = password();
        if (access == false) {
            return;
        }
        String calculatorSettingToChange = "";
        while (!"0".equals(calculatorSettingToChange)) {
            calculatorSettingToChange = calculatorSettingsMenu(location);
            switch (calculatorSettingToChange) {
                case "0": {
                    break;
                }
                case "Change the Multiplication Symbol": {
                    changeMultiplicationSymbol();
                    break;
                }
                case "Change the Division Symbol": {
                    changeDivisionSymbol();
                    break;
                }
                case "": {
                    errors.add("Error: Settings\n"
                            + "Error Description: No Option Number Entered");
                    output = "ERROR:\n"
                            + "No Option Number Entered";
                    JOptionPane.showMessageDialog(null, output, "Settings", JOptionPane.ERROR_MESSAGE);
                    break;
                }
                default: {
                    errors.add("Error: Settings\n"
                            + "Error Description: Invalid Option Number Entered");
                    output = "ERROR:\n"
                            + "Invalid Option Number";
                    JOptionPane.showMessageDialog(null, output, "Settings", JOptionPane.ERROR_MESSAGE);
                    break;
                }
            }
        }
    }

    public static String calculatorSettingsMenu(String location) {
        try {
            Object[] options = new Object[2];
            options[0] = "Change the Multiplication Symbol";
            options[1] = "Change the Division Symbol";
            output = "What do you want to change about the calculator?";
            String calculatorSettingToChange = (String) JOptionPane.showInputDialog(null, output, "Calculator Settings", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            if (calculatorSettingToChange == null) {
                return "0";
            }
            return calculatorSettingToChange;
        } catch (Exception error) {
            return "0";
        }
    }

    public static void changeMultiplicationSymbol() {
        String oldMultiplicationSymbol = multiplicationSymbol;
        String multiplicationSymbolChoice;
        try {
            output = "The current multiplication symbol is " + multiplicationSymbol + ".\n"
                    + "How would you like the multiplication symbol to look?\n";
            multiplicationSymbolChoice = JOptionPane.showInputDialog(null, output
                    + "1: ×\n"
                    + "2: *\n"
                    + "3: •\n"
                    + "4: x\n"
                    + "Please choose a multiplication symbol by entering its number.");
            if (multiplicationSymbolChoice == null) {
                multiplicationSymbol = oldMultiplicationSymbol;
                return;
            }
            switch (multiplicationSymbolChoice) {
                case "0": {
                    break;
                }
                case "1": {
                    File multiplicationSymbolTemp = new File(goldStarsFolder, "multiplicationSymbol.txt");
                    multiplicationSymbol = "×";
                    output = "Multiplication symbol updated.";
                    JOptionPane.showMessageDialog(null, output, "Settings", JOptionPane.INFORMATION_MESSAGE);
                    PrintWriter out;
                    try {
                        out = new PrintWriter(multiplicationSymbolTemp);
                        out.append(multiplicationSymbol);
                        out.close();
                    } catch (FileNotFoundException e2) {

                    }
                    break;
                }
                case "2": {
                    File multiplicationSymbolTemp = new File(goldStarsFolder, "multiplicationSymbol.txt");
                    multiplicationSymbol = "*";
                    output = "Multiplication symbol updated.";
                    JOptionPane.showMessageDialog(null, output, "Settings", JOptionPane.INFORMATION_MESSAGE);
                    PrintWriter out;
                    try {
                        out = new PrintWriter(multiplicationSymbolTemp);
                        out.append(multiplicationSymbol);
                        out.close();
                    } catch (FileNotFoundException e2) {

                    }
                    break;
                }
                case "3": {
                    File multiplicationSymbolTemp = new File(goldStarsFolder, "multiplicationSymbol.txt");
                    multiplicationSymbol = "•";
                    output = "Multiplication symbol updated.";
                    JOptionPane.showMessageDialog(null, output, "Settings", JOptionPane.INFORMATION_MESSAGE);
                    PrintWriter out;
                    try {
                        out = new PrintWriter(multiplicationSymbolTemp);
                        out.append(multiplicationSymbol);
                        out.close();
                    } catch (FileNotFoundException e2) {

                    }
                    break;
                }
                case "4": {
                    File multiplicationSymbolTemp = new File(goldStarsFolder, "multiplicationSymbol.txt");
                    multiplicationSymbol = "x";
                    output = "Multiplication symbol updated.";
                    JOptionPane.showMessageDialog(null, output, "Settings", JOptionPane.INFORMATION_MESSAGE);
                    PrintWriter out;
                    try {
                        out = new PrintWriter(multiplicationSymbolTemp);
                        out.append(multiplicationSymbol);
                        out.close();
                    } catch (FileNotFoundException e2) {

                    }
                    break;
                }
                default: {
                    errors.add("Error: Settings\n"
                            + "Error Description: Invalid Multiplication Symbol Option Number Entered\n"
                            + "Result: The multiplication symbol is now the default multiplication symbol (×).");
                    output = "ERROR:\n"
                            + "You have entered an invalid multiplication symbol option number,\n"
                            + "so the multiplication symbol will become the default multiplication symbol (×).";
                    JOptionPane.showMessageDialog(null, output, "Settings", JOptionPane.ERROR_MESSAGE);
                    File multiplicationSymbolTemp = new File(goldStarsFolder, "multiplicationSymbol.txt");
                    multiplicationSymbol = "×";
                    PrintWriter out;
                    try {
                        out = new PrintWriter(multiplicationSymbolTemp);
                        out.append(multiplicationSymbol);
                        out.close();
                    } catch (FileNotFoundException e2) {

                    }
                    break;
                }
            }
        } catch (Exception error) {
            return;
        }
    }

    public static void changeDivisionSymbol() {
        String oldDivisionSymbol = divideSymbol;
        String divideSymbolChoice;
        try {
            output = "The current division symbol is " + divideSymbol + ".\n"
                    + "How would you like the division symbol to look?\n";
            divideSymbolChoice = JOptionPane.showInputDialog(null, output
                    + "1: ÷\n"
                    + "2: /\n"
                    + "Please choose a division symbol by entering its number.");
            if (divideSymbolChoice == null) {
                divideSymbol = oldDivisionSymbol;
                return;
            }
            switch (divideSymbolChoice) {
                case "0": {
                    break;
                }
                case "1": {
                    File divisionSymbolTemp = new File(goldStarsFolder, "divideSymbol.txt");
                    divideSymbol = "÷";
                    output = "Division symbol updated.";
                    JOptionPane.showMessageDialog(null, output, "Settings", JOptionPane.INFORMATION_MESSAGE);
                    PrintWriter out;
                    try {
                        out = new PrintWriter(divisionSymbolTemp);
                        out.append(divideSymbol);
                        out.close();
                    } catch (FileNotFoundException e2) {

                    }
                    break;
                }
                case "2": {
                    File divisionSymbolTemp = new File(goldStarsFolder, "divideSymbol.txt");
                    divideSymbol = "/";
                    output = "Division symbol updated.";
                    JOptionPane.showMessageDialog(null, output, "Settings", JOptionPane.INFORMATION_MESSAGE);
                    PrintWriter out;
                    try {
                        out = new PrintWriter(divisionSymbolTemp);
                        out.append(divideSymbol);
                        out.close();
                    } catch (FileNotFoundException e2) {

                    }
                    break;
                }
                default: {
                    errors.add("Error: Settings\n"
                            + "Error Description: Invalid Division Symbol Option Number Entered\n"
                            + "Result: The division symbol is now the default division symbol (÷).");
                    output = "ERROR:\n"
                            + "You have entered an invalid division symbol option number,\n"
                            + "so the division symbol will become the default division symbol (÷).";
                    JOptionPane.showMessageDialog(null, output, "Settings", JOptionPane.ERROR_MESSAGE);
                    divideSymbol = "÷";
                    File divisionSymbolTemp = new File(goldStarsFolder, "divideSymbol.txt");
                    PrintWriter out;
                    try {
                        out = new PrintWriter(divisionSymbolTemp);
                        out.append(divideSymbol);
                        out.close();
                    } catch (FileNotFoundException e2) {

                    }
                    break;
                }
            }
        } catch (Exception error) {
            return;
        }
    }

    public static void notesSettings() {
        boolean access = password();
        if (access == false) {
            return;
        }
        Object[] options = new Object[2];
        options[0] = "Change Whether Notes Search is Case Sensitive";
        options[1] = "Enable or Disable Password-Protected Notes";
        output = "What do you want to change?";
        String generalSettingsChoice = (String) JOptionPane.showInputDialog(null, output, "Notes Settings", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        if ("Change Whether Notes Search is Case Sensitive".equals(generalSettingsChoice)) {
            notesSearchCaseSensitive();
        } else if ("Enable or Disable Password-Protected Notes".equals(generalSettingsChoice)) {
            passwordProtectedNotes();
        }
    }

    public static void notesSearchCaseSensitive() {
        output = "NOTES SETTINGS\n";
        if (notesSearchCaseSensitive == true) {
            output += "The Notes Search is currently case sensitive.\n";
        } else {
            output += "The Notes Search is currently not case sensitive.\n";
        }
        output += "Do you want the Notes Search to be case sensitive?";
        int yOrN = JOptionPane.showConfirmDialog(null, output, "Settings", JOptionPane.YES_NO_OPTION);
        if (yOrN == JOptionPane.YES_OPTION) {
            notesSearchCaseSensitive = true;
            PrintWriter out;
            try {
                out = new PrintWriter("notesSearchCaseSensitive.txt");
                out.append("1");
                out.close();
            } catch (FileNotFoundException e2) {

            }
            JOptionPane.showMessageDialog(null, "Choice Saved", "Settings", JOptionPane.INFORMATION_MESSAGE);
        }
        if (yOrN == JOptionPane.NO_OPTION) {
            notesSearchCaseSensitive = false;
            PrintWriter out;
            try {
                out = new PrintWriter("notesSearchCaseSensitive.txt");
                out.append("0");
                out.close();
            } catch (FileNotFoundException e2) {

            }
            JOptionPane.showMessageDialog(null, "Choice Saved", "Settings", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void passwordProtectedNotes() {
        output = "PASSWORD-PROTECTED NOTES\n";
        if (notesSearchCaseSensitive == true) {
            output += "Notes are currently password-protected.\n";
        } else {
            output += "Notes are currently not password-protected.\n";
        }
        output += "Do you want notes to be password-protected?";
        int yOrN = JOptionPane.showConfirmDialog(null, output, "Settings", JOptionPane.YES_NO_OPTION);
        if (yOrN == JOptionPane.YES_OPTION) {
            passwordProtectedNotes = true;
            PrintWriter out;
            try {
                out = new PrintWriter("passwordProtectedNotes.txt");
                out.append("1");
                out.close();
            } catch (FileNotFoundException e2) {

            }
            JOptionPane.showMessageDialog(null, "Choice Saved", "Settings", JOptionPane.INFORMATION_MESSAGE);
        }
        if (yOrN == JOptionPane.NO_OPTION) {
            passwordProtectedNotes = false;
            PrintWriter out;
            try {
                out = new PrintWriter("passwordProtectedNotes.txt");
                out.append("0");
                out.close();
            } catch (FileNotFoundException e2) {

            }
            JOptionPane.showMessageDialog(null, "Choice Saved", "Settings", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void photosSettings() {
        boolean access = password();
        if (access == false) {
            return;
        }
        Object[] options = new Object[2];
        options[0] = "Change Whether Photos Search is Case Sensitive";
        options[1] = "Enable or Disable Password-Protected Photos";
        output = "What do you want to change?";
        String generalSettingsChoice = (String) JOptionPane.showInputDialog(null, output, "Photos Settings", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        if ("Change Whether Photos Search is Case Sensitive".equals(generalSettingsChoice)) {
            photosSearchCaseSensitive();
        } else if ("Enable or Disable Password-Protected Photos".equals(generalSettingsChoice)) {
            passwordProtectedPhotos();
        }
    }

    public static void photosSearchCaseSensitive() {
        output = "PHOTOS SETTINGS\n";
        if (notesSearchCaseSensitive == true) {
            output += "The Photos Search is currently case sensitive.\n";
        } else {
            output += "The Photos Search is currently not case sensitive.\n";
        }
        output += "Do you want the Photos Search to be case sensitive?";
        int yOrN = JOptionPane.showConfirmDialog(null, output, "Settings", JOptionPane.YES_NO_OPTION);
        if (yOrN == JOptionPane.YES_OPTION) {
            photosSearchCaseSensitive = true;
            PrintWriter out;
            try {
                out = new PrintWriter("photosSearchCaseSensitive.txt");
                out.append("1");
                out.close();
            } catch (FileNotFoundException e2) {

            }
            JOptionPane.showMessageDialog(null, "Choice Saved", "Settings", JOptionPane.INFORMATION_MESSAGE);
        }
        if (yOrN == JOptionPane.NO_OPTION) {
            photosSearchCaseSensitive = false;
            PrintWriter out;
            try {
                out = new PrintWriter("photosSearchCaseSensitive.txt");
                out.append("0");
                out.close();
            } catch (FileNotFoundException e2) {

            }
            JOptionPane.showMessageDialog(null, "Choice Saved", "Settings", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void passwordProtectedPhotos() {
        output = "PASSWORD-PROTECTED PHOTOS\n";
        if (notesSearchCaseSensitive == true) {
            output += "Photos are currently password-protected.\n";
        } else {
            output += "Photos are currently not password-protected.\n";
        }
        output += "Do you want photos to be password-protected?";
        int yOrN = JOptionPane.showConfirmDialog(null, output, "Settings", JOptionPane.YES_NO_OPTION);
        if (yOrN == JOptionPane.YES_OPTION) {
            passwordProtectedPhotos = true;
            PrintWriter out;
            try {
                out = new PrintWriter("passwordProtectedPhotos.txt");
                out.append("1");
                out.close();
            } catch (FileNotFoundException e2) {

            }
            JOptionPane.showMessageDialog(null, "Choice Saved", "Settings", JOptionPane.INFORMATION_MESSAGE);
        }
        if (yOrN == JOptionPane.NO_OPTION) {
            passwordProtectedPhotos = false;
            PrintWriter out;
            try {
                out = new PrintWriter("passwordProtectedPhotos.txt");
                out.append("0");
                out.close();
            } catch (FileNotFoundException e2) {

            }
            JOptionPane.showMessageDialog(null, "Choice Saved", "Settings", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static boolean changeLanguage() {
        boolean access = password();
        if (access == false) {
            return false;
        }
        try {
            output = "";
            if ("1".equals(language)) {
                output += "The current language is American English.\n";
            } else {
                output += "The current language is British English.\n";
            }
            output += "Please choose a language:";
            Object[] options = {"American English", "British English"};
            String choice = (String) JOptionPane.showInputDialog(null, output, "Change Language", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            if (choice == null) {
                return false;
            }
            switch (choice) {
                case "American English":
                default: {
                    language = "1";
                    output = "Language set to American English.";
                    JOptionPane.showMessageDialog(null, output, "Change Language", JOptionPane.INFORMATION_MESSAGE);
                    break;
                }
                case "British English": {
                    language = "2";
                    output = "Language set to British English.";
                    JOptionPane.showMessageDialog(null, output, "Change Language", JOptionPane.INFORMATION_MESSAGE);
                    break;
                }
            }
            File languageTemp = new File(goldStarsFolder, "language.txt");
            PrintWriter out;
            try {
                out = new PrintWriter(languageTemp);
                out.append(language);
                out.close();
            } catch (FileNotFoundException e2) {

            }
            return true;
        } catch (Exception error) {
            return false;
        }
    }

    public static void searchCaseSensitive() {
        boolean access = password();
        if (access == false) {
            return;
        }
        output = "";
        if (notesSearchCaseSensitive == true) {
            output += "Search is currently case sensitive.\n";
        } else {
            output += "Search is currently not case sensitive.\n";
        }
        output += "Do you want Search to be case sensitive?";
        int yOrN = JOptionPane.showConfirmDialog(null, output, "Search Settings", JOptionPane.YES_NO_OPTION);
        if (yOrN == JOptionPane.YES_OPTION) {
            searchCaseSensitive = true;
            PrintWriter out;
            try {
                out = new PrintWriter("searchCaseSensitive.txt");
                out.append("1");
                out.close();
            } catch (FileNotFoundException e2) {

            }
            JOptionPane.showMessageDialog(null, "Choice Saved", "Search Settings", JOptionPane.INFORMATION_MESSAGE);
        }
        if (yOrN == JOptionPane.NO_OPTION) {
            searchCaseSensitive = false;
            PrintWriter out;
            try {
                out = new PrintWriter("searchCaseSensitive.txt");
                out.append("0");
                out.close();
            } catch (FileNotFoundException e2) {

            }
            JOptionPane.showMessageDialog(null, "Choice Saved", "Search Settings", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void errorLogSearchCaseSensitive() {
        boolean access = password();
        if (access == false) {
            return;
        }
        output = "";
        if (notesSearchCaseSensitive == true) {
            output += "The Error Log Search is currently case sensitive.\n";
        } else {
            output += "The Error Log Search is currently not case sensitive.\n";
        }
        output += "Do you want the Error Log Search to be case sensitive?";
        int yOrN = JOptionPane.showConfirmDialog(null, output, "System Tools Settings", JOptionPane.YES_NO_OPTION);
        if (yOrN == JOptionPane.YES_OPTION) {
            errorLogSearchCaseSensitive = true;
            PrintWriter out;
            try {
                out = new PrintWriter("errorLogSearchCaseSensitive.txt");
                out.append("1");
                out.close();
            } catch (FileNotFoundException e2) {

            }
            JOptionPane.showMessageDialog(null, "Choice Saved", "System Tools Settings", JOptionPane.INFORMATION_MESSAGE);
        }
        if (yOrN == JOptionPane.NO_OPTION) {
            errorLogSearchCaseSensitive = false;
            PrintWriter out;
            try {
                out = new PrintWriter("errorLogSearchCaseSensitive.txt");
                out.append("0");
                out.close();
            } catch (FileNotFoundException e2) {

            }
            JOptionPane.showMessageDialog(null, "Choice Saved", "System Tools Settings", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void changePassword() {
        boolean access = password();
        if (access == false) {
            return;
        }
        String tempOutput = "CHANGE PASSWORD\n"
                + "Please enter your new password:";
        setPassword(tempOutput);
    }

    public static class CommandsListener implements MouseListener {
        public void mousePressed(MouseEvent e) {
            commands();
        }

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    public static class TroubleshootProblemsListener implements MouseListener {
        public void mousePressed(MouseEvent e) {
            troubleshootProblems();
        }

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    public static class ErrorLogListener implements MouseListener {
        public void mousePressed(MouseEvent e) {
            errorLog();
        }

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    public static class SearchErrorLogListener implements MouseListener {
        public void mousePressed(MouseEvent e) {
            errorLogSearch();
        }

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    public static void commands() {
        boolean continueCommands = true;
        String input = new String();
        try {
            while (continueCommands) {
                input = JOptionPane.showInputDialog("Please enter your command: ");
                if (input == null) {
                    break;
                }
                input = input.toLowerCase();
                if (input.indexOf("close") >= 0) {
                    continueCommands = false;
                } else if (input.indexOf("add") >= 0 ||
                        input.indexOf("+") >= 0) {
                    add();
                } else if (input.indexOf("subtract") >= 0) {
                    subtract2Numbers();
                } else if (input.indexOf("multiply") >= 0 ||
                        input.indexOf("multiplication") >= 0 ||
                        input.indexOf("*") >= 0) {
                    multiply2Numbers();
                } else if (input.indexOf("divide") >= 0 ||
                        input.indexOf("division") >= 0) {
                    divide2Numbers();
                } else if (input.indexOf("square root") >= 0) {
                    squareRoot();
                } else if (input.indexOf("cube root") >= 0) {
                    cubeRoot();
                } else if (input.indexOf("raise a number to a power") >= 0) {
                    raiseANumberToAPower();
                } else if (input.indexOf("quadratic equation") >= 0 &&
                        input.indexOf("complex") <= 0) {
                    quadraticEquation();
                } else if (input.indexOf("complex quadratic equation") >= 0) {
                    complexQuadraticEquation();
                } else if (input.indexOf("absolute value") >= 0) {
                    absoluteValue();
                } else if (input.indexOf("convert from degrees to radians") >= 0) {
                    degreesToRadians();
                } else if (input.indexOf("convert from radians to degrees") >= 0) {
                    radiansToDegrees();
                } else if (input.indexOf("log base 10") >= 0 ||
                        input.indexOf("log base ten") >= 0) {
                    logBase10();
                } else if (input.indexOf("natural log") >= 0 ||
                        input.indexOf("log base e") >= 0) {
                    naturalLog();
                } else if (input.indexOf("create a note") >= 0 ||
                        input.indexOf("create note") >= 0) {
                    createNote();
                } else if (input.indexOf("view a note") >= 0 ||
                        input.indexOf("view note") >= 0) {
                    viewNote();
                } else if (input.indexOf("edit a note") >= 0 ||
                        input.indexOf("edit note") >= 0) {
                    editNote();
                } else if (input.indexOf("import note") >= 0) {
                    importNotes();
                } else if (input.indexOf("search notes") >= 0 ||
                        input.indexOf("search your notes") >= 0) {
                    searchNotes();
                } else if (input.indexOf("import photo") >= 0 ||
                        input.indexOf("import a photo") >= 0) {
                    importPhotos();
                } else if (input.indexOf("view photo") >= 0 ||
                        input.indexOf("view a photo") >= 0) {
                    viewPhotos();
                } else if (input.indexOf("search photos") >= 0 ||
                        input.indexOf("search my photos") >= 0) {
                    searchPhotos();
                } else if (input.indexOf("launch gold stars talk") >= 0) {
                    goldStarsTalk("Hello, how are you?");
                } else if (input.indexOf("customize gold stars talk") >= 0) {
                    customizeGoldStarsTalk();
                } else if (input.indexOf("gold stars stories") >= 0 ||
                        input.indexOf("story") >= 0) {
                    goldStarsStories();
                } else if (input.indexOf("files") >= 0) {
                    files();
                } else if (input.indexOf("change language") >= 0) {
                    boolean access = password();
                    if (access == false) {
                        return;
                    }
                    changeLanguage();
                } else if (input.indexOf("change theme") >= 0) {
                    boolean access = password();
                    if (access == false) {
                        return;
                    }
                    changeTheme();
                } else if (input.indexOf("change username") >= 0) {
                    boolean access = password();
                    if (access == false) {
                        return;
                    }
                    changeUsername();
                } else if (input.indexOf("calendar settings") >= 0) {
                    changeCalendarFormatMain();
                } else if (input.indexOf("calculator settings") >= 0) {
                    calculatorSettings("Commands");
                } else if (input.indexOf("notes settings") >= 0) {
                    notesSettings();
                } else if (input.indexOf("photos settings") >= 0) {
                    photosSettings();
                } else if (input.indexOf("notes search case sensitive") >= 0) {
                    notesSearchCaseSensitive();
                } else if (input.indexOf("error log search case sensitive") >= 0 ||
                        input.indexOf("system tools settings") >= 0) {
                    errorLogSearchCaseSensitive();
                } else if (input.indexOf("search case sensitive") >= 0 ||
                        input.indexOf("search settings") >= 0) {
                    searchCaseSensitive();
                } else if (input.indexOf("help") >= 0) {
                    help();
                } else if (input.indexOf("about programs") >= 0) {
                    aboutPrograms();
                } else if (input.indexOf("what's new") >= 0) {
                    whatsNew();
                } else {
                    JOptionPane.showMessageDialog(null, "Command Not Recognized", "System Tools", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        } catch (Exception error) {
            return;
        }
    }

    public static void errorLog() {
        int errorLogChoice;
        if (errors.isEmpty()) {
            output = "There are currently no errors reported!";
            JOptionPane.showMessageDialog(null, output, "Error Log", JOptionPane.INFORMATION_MESSAGE);
            return;
        } else if (errors.size() == 1) {
            output = JOptionPane.showInputDialog(errors.size() + " error has been reported.\n"
                    + "Please enter the number of the error you would like to see,\n"
                    + "or press S to search the Error Log.");
        } else {
            output = JOptionPane.showInputDialog(errors.size() + " errors have been reported.\n"
                    + "Please enter the number of the error you would like to see,\n"
                    + "or press S to search the Error Log.");
        }
        if (("0".equals(output)) || (output == null)) {
            return;
        }
        output = output.toLowerCase();
        if ("s".equals(output)) {
            errorLogSearch();
            return;
        }
        try {
            errorLogChoice = Integer.parseInt(output);
            output = errors.get(errorLogChoice - 1);
            JOptionPane.showMessageDialog(null, output, "Error Log", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception error) {
            errors.add("Error: Error Log");
            JOptionPane.showMessageDialog(null, "ERROR", "Error Log", JOptionPane.ERROR_MESSAGE);
            return;
        }
    }

    public static void errorLogSearch() {
        ArrayList<String> searchResults = new ArrayList<String>();
        String searchTerm;
        searchTerm = JOptionPane.showInputDialog(null, "Please enter your search term:");
        if (searchTerm == null) {
            return;
        }
        String lowerCaseSearchTerm = searchTerm;
        if (errorLogSearchCaseSensitive == false) {
            lowerCaseSearchTerm = searchTerm.toLowerCase();
        }
        for (int i = 0; i < errors.size(); i++) {
            if (errorLogSearchCaseSensitive == false) {
                String temp = errors.get(i);
                temp = temp.toLowerCase();
                if (temp.indexOf(lowerCaseSearchTerm) >= 0) {
                    searchResults.add(errors.get(i));
                }
            } else {
                if (errors.get(i).indexOf(searchTerm) >= 0) {
                    searchResults.add(errors.get(i));
                }
            }
        }
        String errorLogSearchResultsInput = "-1";
        while (!"0".equals(errorLogSearchResultsInput)) {
            if (searchResults.isEmpty()) {
                output = "No search results were found.";
                JOptionPane.showMessageDialog(null, output, "Error Log Search", JOptionPane.INFORMATION_MESSAGE);
                return;
            } else if (searchResults.size() == 1) {
                errorLogSearchResultsInput = JOptionPane.showInputDialog(searchResults.size() + " result was found.\n"
                        + "Please enter the number of the search result to view,\n"
                        + "or enter 0 or press Cancel to return to the Error Log.");
            } else {
                errorLogSearchResultsInput = JOptionPane.showInputDialog(searchResults.size() + " results were found.\n"
                        + "Please enter the number of the search result to view,\n"
                        + "or enter 0 or press Cancel to return to the Error Log.");
            }
            if ((errorLogSearchResultsInput == null) || ("0".equals(errorLogSearchResultsInput))) {
                break;
            }
            try {
                int searchTermViewIndex = Integer.parseInt(errorLogSearchResultsInput);
                searchTermViewIndex--;
                output = "Search Term: " + searchTerm + "\n"
                        + "Search Result " + (searchTermViewIndex + 1) + " of " + searchResults.size() + "\n"
                        + searchResults.get(searchTermViewIndex);
                JOptionPane.showMessageDialog(null, output, "Error Log", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception error) {
                errors.add("Error: Error Log");
                JOptionPane.showMessageDialog(null, "ERROR", "Error Log", JOptionPane.ERROR_MESSAGE);
                break;
            }
        }
    }

    public static void troubleshootProblems() {
        output = "If you encounter a problem in Gold Stars, there are 3 options that you can take.\n"
                + "OPTION 1: RESTART GOLD STARS\n"
                + "Sometimes, a quick restart of Gold Stars can solve the problem!\n"
                + "OPTION 2: CHECK THE ERROR LOG\n"
                + "You can check the Error Log to see where something went wrong.\n"
                + "Tip: If you know the program that the error occured in, you can search the error log for the name of that program.\n"
                + "OPTION 3: CONTACT THE GOLD STARS TEAM AT GREEN FOREST TECHNOLOGY\n"
                + "If the other options don't work, you can send an e-mail to greenforesttechnology@gmail.com, and we'll attempt to respond as soon as we can!";
        JOptionPane.showMessageDialog(null, output, "Troubleshoot Problems", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void notifications() {
        String notifications = "";
        Calendar calendar = Calendar.getInstance();
        ArrayList<String> eventsToday = new ArrayList<String>();
        for (int i = 0; i < calendarEvents.size(); i++) {
            String temp = calendarEvents.get(i);
            int year, month, date;
            year = Integer.parseInt(temp.substring(0, 4));
            month = Integer.parseInt(temp.substring(4, 6));
            date = Integer.parseInt(temp.substring(6, 8));
            String event = temp.substring(8);
            if (year == calendar.get(Calendar.YEAR) && month == (calendar.get(Calendar.MONTH) + 1) && date == calendar.get(Calendar.DATE)) {
                eventsToday.add(event);
            }
        }
        if (eventsToday.isEmpty()) {
            notifications += "No calendar events today.\n";
        }
        if (!eventsToday.isEmpty()) {
            if (eventsToday.size() == 1) {
                notifications += eventsToday.size() + " Calendar Event Today\n";
            }
            if (eventsToday.size() != 1) {
                notifications += eventsToday.size() + " Calendar Events Today\n";
            }
        }
        if (notes.size() == 1) {
            notifications += notes.size() + " Note Saved\n";
        }
        if (notes.size() != 1) {
            notifications += notes.size() + " Notes Saved\n";
        }
        if (photoNames.size() == 1) {
            notifications += photoNames.size() + " Photo Saved\n";
        }
        if (photoNames.size() != 1) {
            notifications += photoNames.size() + " Photos Saved\n";
        }
        if (games.size() == 1) {
            notifications += games.size() + " Game Saved\n";
        }
        if (games.size() != 1) {
            notifications += games.size() + " Games Saved\n";
        }
        if (errors.size() == 1) {
            notifications += errors.size() + " Error Reported\n";
        }
        if (errors.size() != 1) {
            notifications += errors.size() + " Errors Reported\n";
        }
        JOptionPane.showMessageDialog(null, notifications, "Notifications", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void aboutPrograms() {
        String programInfoChoice;
        output = "Which program do you want to view information about?";
        Object[] options = new Object[13];
        options[0] = "Calendar";
        options[1] = "Calculator";
        options[2] = "Notes";
        options[3] = "Photos";
        options[4] = "Gold Center";
        options[5] = "System Tools";
        options[6] = "Settings";
        options[7] = "Gold Stars Talk";
        options[8] = "Gold Stars Stories";
        options[9] = "Files";
        options[10] = "Notifications";
        options[11] = "Search";
        options[12] = "Help";
        programInfoChoice = (String) JOptionPane.showInputDialog(null, output, "About Programs", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        if (programInfoChoice == null) {
            return;
        }
        switch (programInfoChoice) {
            case "0": {
                break;
            }
            case "Calendar": {
                output = "ABOUT THE CALENDAR\n"
                        + "This program can display the current date in three formats.\n"
                        + "The format in which the date is displayed can be changed in the Calendar Settings.\n"
                        + "You can also create calendar events. You can then view the events that you have that day.\n"
                        + "Note: Not all of today's calendar events may show in 'View Today's Events.' You may need to adjust the size of the window to view more events.";
                JOptionPane.showMessageDialog(null, output, "About Programs", JOptionPane.INFORMATION_MESSAGE);
                break;
            }
            case "Calculator": {
                output = "ABOUT THE CALCULATOR\n"
                        + "This program can:\n"
                        + "--Add, subtract, multiply, and divide two numbers.\n"
                        + "--Find the square root of a number and the cube root of a number.\n"
                        + "--Raise a number to a power.\n"
                        + "--Add and multiply more than two numbers together.\n"
                        + "--Solve a quadratic equation.\n"
                        + "--Convert from degrees to radians and from radians to degrees.\n"
                        + "--Find the log base 10 of a number and the natural log (log base e) of a number.\n"
                        + "--Compute using e (~" + Math.E + ").\n"
                        + "--Compute using pi (~" + Math.PI + ").";
                JOptionPane.showMessageDialog(null, output, "About Programs", JOptionPane.INFORMATION_MESSAGE);
                break;
            }
            case "Notes": {
                output = "ABOUT NOTES\n"
                        + "This program can save notes that you type.\n"
                        + "You can create, edit, search, rename, and view notes.\n"
                        + "In Notes Settings, you can adjust whether the Notes Search is case sensitive or not.\n"
                        + "You can also enable and disable password-protected notes.";
                JOptionPane.showMessageDialog(null, output, "About Programs", JOptionPane.INFORMATION_MESSAGE);
                break;
            }
            case "Photos": {
                output = "ABOUT PHOTOS\n"
                        + "In this program, you can import photos. You can then view the photos that you have imported.\n"
                        + "You can also search your photos.\n"
                        + "In Photos Settings, you can adjust whether the Photos Search is case sensitive or not.\n"
                        + "You can also enable and disable password-protected photos.";
                JOptionPane.showMessageDialog(null, output, "About Programs", JOptionPane.INFORMATION_MESSAGE);
                break;
            }
            case "Gold Stars Talk": {
                switch (language) {
                    case "1":
                    default: {
                        output = "ABOUT GOLD STARS TALK\n"
                                + "Have a conversation with the computer by typing messages to it!\n"
                                + "You can use this for fun, or get a task done quickly!\n"
                                + "For example, you can talk to Gold Stars Talk about your favorite flavor of ice cream, "
                                + "or you can ask it to change your username for you!";
                        JOptionPane.showMessageDialog(null, output, "About Programs", JOptionPane.INFORMATION_MESSAGE);
                        break;
                    }
                    case "2": {
                        output = "ABOUT GOLD STARS TALK\n"
                                + "You can have a conversation with the computer by typing messages to it!\n"
                                + "This can be used for fun, or get a task done quickly!\n"
                                + "For example, you can talk to Gold Stars Talk about your favourite flavour of ice cream, "
                                + "or you can ask it to change your username for you!";
                        JOptionPane.showMessageDialog(null, output, "About Programs", JOptionPane.INFORMATION_MESSAGE);
                        break;
                    }
                }
                break;
            }
            case "Gold Stars Stories": {
                output = "ABOUT GOLD STARS STORIES\n"
                        + "You can read many short stories in Gold Stars Stories!";
                JOptionPane.showMessageDialog(null, output, "About Programs", JOptionPane.INFORMATION_MESSAGE);
                break;
            }
            case "Files": {
                output = "ABOUT FILES\n"
                        + "In Files, you can view notes and photos that you have saved to Gold Stars.";
                JOptionPane.showMessageDialog(null, output, "About Programs", JOptionPane.INFORMATION_MESSAGE);
                break;
            }
            case "Notifications": {
                output = "ABOUT NOTIFICATIONS\n"
                        + "In Notifications, you can view:\n"
                        + "--The number of calendar events you have today.\n"
                        + "--The number of notes you have saved.\n"
                        + "--The number of photos you have saved.\n"
                        + "--The number of errors that have been reported since you opened Gold Stars.";
                JOptionPane.showMessageDialog(null, output, "About Programs", JOptionPane.INFORMATION_MESSAGE);
                break;
            }
            case "System Tools": {
                output = "ABOUT SYSTEM TOOLS\n"
                        + "System Tools contains Commands, the Error Log, Error Log Search, an option to Troubleshoot Problems, and an option to shut down.\n"
                        + "In Commands, you can enter a command, and it will be attempted to be executed.\n"
                        + "For example, if you type 'app store,' then Commands will open the Gold Stars App Store.\n"
                        + "The Error Log is a report of your errors.\n"
                        + "You can search the Error Log in the Error Log Search. You can adjust the case sensitivity of the Error Log Serach in System Tools Settings.\n"
                        + "The option to troubleshoot problems gives you options to fix problems that may occur in Gold Stars.";
                JOptionPane.showMessageDialog(null, output, "About Programs", JOptionPane.INFORMATION_MESSAGE);
                break;
            }
            case "Search": {
                output = "ABOUT SEARCH\n"
                        + "Entering a search term into Search will search your saved notes and the Error Log for your search term.\n"
                        + "You can adjust the case sensitivity of Search in Search Settings.";
                JOptionPane.showMessageDialog(null, output, "About Programs", JOptionPane.INFORMATION_MESSAGE);
                break;
            }
            case "Gold Center": {
                output = "ABOUT GOLD CENTER\n"
                        + "Gold Center contains GoldScript, Gold Games, and Gold Apps.\n"
                        + "GoldScript is a set of instructions on how to make games and apps compatible with Gold Stars.\n"
                        + "In Gold Games, you can view information about GoldScript-compatible games.\n"
                        + "In Gold Apps, you can view information about GoldScript-compatible apps.";
                JOptionPane.showMessageDialog(null, output, "About Programs", JOptionPane.INFORMATION_MESSAGE);
                break;
            }
            case "Settings": {
                output = "ABOUT SETTINGS\n"
                        + "In Settings, you can:\n"
                        + "--Change the language between American English and British English.\n"
                        + "--Change the theme.\n"
                        + "--Change your username.\n"
                        + "--Set a wallpaper.\n"
                        + "--Change your password.\n"
                        + "--Change the format of the calendar.\n"
                        + "--Change the multiplication and division symbols.\n"
                        + "--Change whether the Notes Search is case sensitive.\n"
                        + "--Enable or disable password-protected notes.\n"
                        + "--Change whether the Photos Search is case sensitive.\n"
                        + "--Enable or disable password-protected photos.\n"
                        + "--Change whether Search is case sensitive.\n"
                        + "--Change whether the Error Log Search is case sensitive.\n"
                        + "NOTE: In order for some changes to take place, you may have to restart Gold Stars.";
                JOptionPane.showMessageDialog(null, output, "About Programs", JOptionPane.INFORMATION_MESSAGE);
                break;
            }
            case "Help": {
                output = "ABOUT HELP\n"
                        + "The Help app explains how to customize Gold Stars, suggests possible things to say to Gold Stars Talk, and more!";
                JOptionPane.showMessageDialog(null, output, "About Programs", JOptionPane.INFORMATION_MESSAGE);
                break;
            }
            default: {
                errors.add("Error: About Programs\n"
                        + "Error Description: Invalid Specified Program");
                output = "ERROR:\n"
                        + "Invalid Specified Program";
                JOptionPane.showMessageDialog(null, output, "About Programs", JOptionPane.ERROR_MESSAGE);
                break;
            }
        }
    }

    public static void historyOfGoldStars() {
        String versionChoice;
        output = "Please choose a version of Gold Stars to view information about:";
        Object[] options = new Object[20];
        options[0] = "Gold Stars 1";
        options[1] = "Gold Stars 2";
        options[2] = "Gold Stars 3";
        options[3] = "Gold Stars 4";
        options[4] = "Gold Stars 5";
        options[5] = "Gold Stars 6";
        options[6] = "Gold Stars 7";
        options[7] = "Gold Stars 8";
        options[8] = "Gold Stars 9";
        options[9] = "Gold Stars X (Version 10)";
        options[10] = "Gold Stars X 2 Fire (Veresion 11)";
        options[11] = "Gold Stars X 3 Water (Version 12)";
        options[12] = "Gold Stars X 4 Air (Version 13)";
        options[13] = "Gold Stars X 5 Sand (Version 14)";
        options[14] = "Gold Stars X 6 Snow (Version 15)";
        options[15] = "Gold Stars X 7 Cloud (Version 16)";
        options[16] = "Gold Stars X 8 Rain (Version 17)";
        options[17] = "Gold Stars X 9 Thunder (Version 18)";
        options[18] = "Gold Stars X 10 Storm (Version 19)";
        options[19] = "Gold Stars 20";
        versionChoice = (String) JOptionPane.showInputDialog(null, output, "History of Gold Stars", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        if (versionChoice == null) {
            return;
        }
        switch (versionChoice) {
            case "Gold Stars 1": {
                output = "The first version of Gold Stars, Gold Stars 1.0, was released on August 19, 2013.\n"
                        + "The next minor version, Gold Stars 1.1, was also released on August 19, 2013.";
                JOptionPane.showMessageDialog(null, output, "History of Gold Stars", JOptionPane.INFORMATION_MESSAGE);
                break;
            }
            case "Gold Stars 2": {
                output = "The Notes app was added to Gold Stars in Gold Stars 2.0. Gold Stars 2.0 was released on September 8, 2013.\n"
                        + "Gold Stars 2 had 2 minor versions: Gold Stars 2.1 and Gold Stars 2.2.\n"
                        + "These versions were released on September 19, 2013 and September 25, 2013, respectively.";
                JOptionPane.showMessageDialog(null, output, "History of Gold Stars", JOptionPane.INFORMATION_MESSAGE);
                break;
            }
            case "Gold Stars 3": {
                output = "The interface of Gold Stars was changed in Gold Stars 3.\n"
                        + "Prior to Gold Stars 3, Gold Stars only displayed lines of text.\n"
                        + "This was changed in Gold Stars 3, in which dialog boxes were added to Gold Stars.\n"
                        + "Gold Stars 3.0 was released on October 17, 2013.\n"
                        + "Gold Stars 3 had one minor version, Gold Stars 3.1. Gold Stars 3.1 was released on October 24, 2013.";
                JOptionPane.showMessageDialog(null, output, "History of Gold Stars", JOptionPane.INFORMATION_MESSAGE);
                break;
            }
            case "Gold Stars 4": {
                output = "Gold Stars Talk was added to Gold Stars in Gold Stars 4.\n"
                        + "Gold Stars 4.0 was released on December 11, 2013.\n"
                        + "Gold Stars 4 had three minor versions: Gold Stars 4.1, Gold Stars 4.2, and Gold Stars 4.3.\n"
                        + "These versions were released on January 8, 2014, January 14, 2014, and January 30, 2014, respectively.";
                JOptionPane.showMessageDialog(null, output, "History of Gold Stars", JOptionPane.INFORMATION_MESSAGE);
                break;
            }
            case "Gold Stars 5": {
                output = "The Gold Stars App Store was added to Gold Stars in Gold Stars 5.\n"
                        + "Gold Stars 5.0 was released on February 27, 2014.\n"
                        + "Gold Stars 5.0 had one update, Gold Stars 5.0.1, which was also released on February 27, 2014.\n"
                        + "Gold Stars 5 had one minor version, Gold Stars 5.1, which was released on March 3, 2014.\n"
                        + "Gold Stars 5.1 had three updates: Gold Stars 5.1.1, Gold Stars 5.1.2, and Gold Stars 5.1.3.\n"
                        + "Gold Stars 5.1.1 and Gold Stars 5.1.2 were both released on March 3, 2014. Gold Stars 5.1.3 was released on March 4, 2014.";
                JOptionPane.showMessageDialog(null, output, "History of Gold Stars", JOptionPane.INFORMATION_MESSAGE);
                break;
            }
            case "Gold Stars 6": {
                output = "The Search feature was added to Gold Stars in Gold Stars 6.\n"
                        + "Gold Stars 6.0 was released on March 28, 2014.\n"
                        + "Gold Stars 6 had one minor version, Gold Stars 6.1, which was released on April 30, 2014.";
                JOptionPane.showMessageDialog(null, output, "History of Gold Stars", JOptionPane.INFORMATION_MESSAGE);
                break;
            }
            case "Gold Stars 7": {
                output = "Commands was added to Gold Stars in Gold Stars 7.\n"
                        + "Gold Stars 7.0 was released on May 18, 2014.\n"
                        + "Gold Stars 7 had two minor versions: Gold Stars 7.1 and Gold Stars 7.2.\n"
                        + "These versions were released on June 1, 2014 and June 7, 2014, respectively.\n"
                        + "Gold Stars 7.2 had one update, Gold Stars 7.2.1. Gold Stars 7.2.1 was released on June 8, 2014.";
                JOptionPane.showMessageDialog(null, output, "History of Gold Stars", JOptionPane.INFORMATION_MESSAGE);
                break;
            }
            case "Gold Stars 8": {
                output = "In Gold Stars 8, the Program Launcher was replaced with a new interface. The new interface consisted of a window with a menu bar at the top.\n"
                        + "Gold Stars 8.0 was released on July 6, 2014.\n"
                        + "Gold Stars 8 had one minor version, Gold Stars 8.1, which was released on July 9, 2014.";
                JOptionPane.showMessageDialog(null, output, "History of Gold Stars", JOptionPane.INFORMATION_MESSAGE);
                break;
            }
            case "Gold Stars 9": {
                output = "The ability to change the background color of Gold Stars was added in Gold Stars 9.\n"
                        + "Gold Stars 9.0 was released on July 12, 2014.\n"
                        + "Gold Stars 9 had five minor versions: Gold Stars 9.1, Gold Stars 9.2, Gold Stars 9.3, Gold Stars 9.4, and Gold Stars 9.5.\n"
                        + "These versions of Gold Stars were released on July 21, 2014, July 22, 2014, July 24, 2014, July 25, 2014, and July 27, 2014, respectively.";
                JOptionPane.showMessageDialog(null, output, "History of Gold Stars", JOptionPane.INFORMATION_MESSAGE);
                break;
            }
            case "Gold Stars X (Version 10)": {
                output = "Gold Stars X, the tenth version of Gold Stars, was released on August 23, 2014.\n"
                        + "Gold Stars X brought many improvements to Gold Stars, including the Star Menu, a search box on the menu bar, and the ability to multitask with certain apps.";
                JOptionPane.showMessageDialog(null, output, "History of Gold Stars", JOptionPane.INFORMATION_MESSAGE);
                break;
            }
            case "Gold Stars X 2 Fire (Version 11)": {
                output = "Gold Stars X 2 Fire is the eleventh version of Gold Stars. It was released on September 12, 2014.\n"
                        + "Some of the additions in Gold Stars X 2 Fire were the Photos app and Calendar Events.";
                JOptionPane.showMessageDialog(null, output, "History of Gold Stars", JOptionPane.INFORMATION_MESSAGE);
                break;
            }
            case "Gold Stars X 3 Water (Version 12)": {
                output = "Gold Stars X 3 Water is the twelfth version of Gold Stars. It was released on September 22, 2014.\n"
                        + "Some of the additions in Gold Stars X 3 were the Notifications app and a redesigned menu bar.\n"
                        + "On September 25, 2014, Gold Stars X 3 Water Update 1 was released. It added the ability to import photos from Gold Stars Talk and Commands.\n"
                        + "Gold Stars X 3 Water Update 2 was also released on September 25, 2014. It added the ability to view and search photos from Gold Stars Talk and Commands.";
                JOptionPane.showMessageDialog(null, output, "History of Gold Stars", JOptionPane.INFORMATION_MESSAGE);
                break;
            }
            case "Gold Stars X 4 Air (Version 13)": {
                output = "Gold Stars X 4 Air is the thirteenth version of Gold Stars. It was released on October 20, 2014.\n"
                        + "It includes a new interface for both the main Gold Stars screen and many of the Gold Stars apps.";
                JOptionPane.showMessageDialog(null, output, "History of Gold Stars", JOptionPane.INFORMATION_MESSAGE);
                break;
            }
            case "Gold Stars X 5 Sand (Version 14)": {
                output = "Gold Stars X 5 Sand is the fourteenth version of Gold Stars. It was released on December 6, 2014.\n"
                        + "The main addition in Gold Stars X 5 Sand is Gold Stars Stories.\n"
                        + "Gold Stars X 5 Sand currently has one minor version, Gold Stars X 5.1 Sand (Version 14.1). It was released on December 15, 2014.";
                JOptionPane.showMessageDialog(null, output, "History of Gold Stars", JOptionPane.INFORMATION_MESSAGE);
                break;
            }
            case "Gold Stars X 6 Snow (Version 15)": {
                output = "Gold Stars X 6 Snow is the fifteenth version of Gold Stars. It was released on December 19, 2014.\n"
                        + "The main additions in Gold Stars X 6 Snow are Games and GoldScript.";
                JOptionPane.showMessageDialog(null, output, "History of Gold Stars", JOptionPane.INFORMATION_MESSAGE);
                break;
            }
            case "Gold Stars X 7 Cloud (Version 16)": {
                output = "Gold Stars X 7 Cloud is the sixteenth version of Gold Stars. It was released on January 17, 2015.\n"
                        + "Many things have been added or changed in Gold Stars X 7 Cloud, including the addition of a Files app and "
                        + "changes to the interfaces of the Calendar, Calculator, and Settings apps.";
                JOptionPane.showMessageDialog(null, output, "History of Gold Stars", JOptionPane.INFORMATION_MESSAGE);
                break;
            }
            case "Gold Stars X 8 Rain (Version 17)": {
                output = "Gold Stars X 8 Rain is the seventeenth version of Gold Stars. It was released on January 29, 2015.\n"
                        + "The addition of a Help app and an updated System Tools interface are among the changes in Gold Stars X 8 Rain.";
                JOptionPane.showMessageDialog(null, output, "History of Gold Stars", JOptionPane.INFORMATION_MESSAGE);
                break;
            }
            case "Gold Stars X 9 Thunder (Version 18)": {
                output = "Gold Stars X 9 Thunder is the eighteenth version of Gold Stars. It was released on February 15, 2015.\n"
                        + "In Gold Stars X 9 Thunder, a menu bar was added to the home screen, themes took effect across all of Gold Stars, "
                        + "and default wallpapers were included.";
                JOptionPane.showMessageDialog(null, output, "History of Gold Stars", JOptionPane.INFORMATION_MESSAGE);
                break;
            }
            case "Gold Stars X 10 Storm (Version 19)": {
                output = "Gold Stars X 10 Storm is the nineteenth version of Gold Stars. It was released on February 16, 2015.\n"
                        + "The main addition in Gold Stars X 10 Storm is the Gold Center.";
                JOptionPane.showMessageDialog(null, output, "History of Gold Stars", JOptionPane.INFORMATION_MESSAGE);
                break;
            }
            case "Gold Stars 20": {
                output = "Gold Stars 20 is a major update to Gold Stars. It is currently in beta status.\n"
                        + "The main features of Gold Stars 20 are an updated main interface of Gold Stars,\n"
                        + "updates to the interfaces of Notes, Photos, About, and Gold Stars Talk,\n"
                        + "and a new file-saving system.";
                JOptionPane.showMessageDialog(null, output, "History of Gold Stars", JOptionPane.INFORMATION_MESSAGE);
                break;
            }
            default: {
                break;
            }
        }
    }

    public static void whatsNew() {
        output = "--The main interface of Gold Stars has been updated.\n"
                + "--The interfaces of Notes, Photos, About, and Gold Stars Talk have been updated.\n"
                + "--Added the option to find the sine, the cosine, and the tangent of a number in the calculator.\n"
                + "--Some Gold Stars files are now saved to a Gold Stars folder.\n"
                + "--Support for wallpapers has been removed.\n"
                + "--Other minor changes.";
        JOptionPane.showMessageDialog(null, output, "What's New in " + name, JOptionPane.INFORMATION_MESSAGE);
    }

    public static void goldGames() {
        if (games.isEmpty()) {
            output = "You don't have any games yet!";
            JOptionPane.showMessageDialog(null, output, "Gold Games", JOptionPane.INFORMATION_MESSAGE);
        } else {
            output = JOptionPane.showInputDialog(null, "Please enter the number of a game.\n"
                    + "You currently have " + games.size() + " game(s).");
            if (output == null) {
                return;
            }
            try {
                int viewGamesChoice = Integer.parseInt(output);
                output = games.get(viewGamesChoice - 1);
                JOptionPane.showMessageDialog(null, output, "Gold Games", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception error) {
                errors.add("Error: Gold Games");
                JOptionPane.showMessageDialog(null, "ERROR", "Gold Games", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
    }

    public static void goldApps() {
        if (apps.isEmpty()) {
            output = "You don't have any apps yet!";
            JOptionPane.showMessageDialog(null, output, "Gold Apps", JOptionPane.INFORMATION_MESSAGE);
        } else {
            output = JOptionPane.showInputDialog(null, "Please enter the number of an app.\n"
                    + "You currently have " + apps.size() + " app(s).");
            if (output == null) {
                return;
            }
            try {
                int viewAppsChoice = Integer.parseInt(output);
                output = apps.get(viewAppsChoice - 1);
                JOptionPane.showMessageDialog(null, output, "Gold Apps", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception error) {
                errors.add("Error: Gold Apps");
                JOptionPane.showMessageDialog(null, "ERROR", "Gold Apps", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
    }

    public static void downloadGames() {
        int gameNum = 0;
        while (gameNum != -1) {
            try {
                games.add(new Scanner(new File("game" + gameNum + ".txt")).useDelimiter("\\Z").next());
                gameNum++;
            } catch (FileNotFoundException e) {
                gameNum = -1;
            }
        }
    }

    public static void downloadApps() {
        int appNum = 0;
        while (appNum != -1) {
            try {
                apps.add(new Scanner(new File("app" + appNum + ".txt")).useDelimiter("\\Z").next());
                appNum++;
            } catch (FileNotFoundException e) {
                appNum = -1;
            }
        }
    }

    public static class WhatIsGoldScriptListener implements MouseListener {
        public void mousePressed(MouseEvent e) {
            whatIsGoldScript();
        }

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    public static class GoldScriptStep1Listener implements MouseListener {
        public void mousePressed(MouseEvent e) {
            goldScriptStep1();
        }

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    public static class GoldScriptStep2Listener implements MouseListener {
        public void mousePressed(MouseEvent e) {
            goldScriptStep2();
        }

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    public static class Step1Listener implements MouseListener {
        public void mousePressed(MouseEvent e) {
            step1();
        }

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    public static class Step2Listener implements MouseListener {
        public void mousePressed(MouseEvent e) {
            step2();
        }

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    public static class GoldGamesListener implements MouseListener {
        public void mousePressed(MouseEvent e) {
            goldGames();
        }

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    public static class GoldAppsListener implements MouseListener {
        public void mousePressed(MouseEvent e) {
            goldApps();
        }

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    public static void whatIsGoldScript() {
        output = "GoldScript is a set of instructions on how to make your apps compatible with Gold Stars.\n"
                + "You can make information from your games appear in Gold Games, and you can make information from\n"
                + "\tyour apps appear in Gold Apps.";
        JFrame whatIsGoldScriptFrame = new JFrame("What is GoldScript?");
        whatIsGoldScriptFrame.setSize(750, 375);
        JTextArea whatIsGoldScriptTextArea = new JTextArea(output);
        whatIsGoldScriptTextArea.setEditable(false);
        whatIsGoldScriptTextArea.setFont(new java.awt.Font(null, Font.PLAIN, 14));
        whatIsGoldScriptFrame.add(whatIsGoldScriptTextArea);
        whatIsGoldScriptFrame.setVisible(true);

    }

    public static void goldScriptStep1() {
        output = "Search the user's Gold Stars folder (the folder that contains Gold Stars) for a file named game0.txt.\n"
                + "If a file named game0.txt is found, search for game1.txt. If game1.txt is found, search for game2.txt.\n"
                + "Continue searching until no more files are found.\n"
                + "Make note of the highest number file found.\n"
                + "Files from your game should be saved to the user's Gold Stars folder as .txt files with the word 'game' followed by\n"
                + "\tthe highest number file found plus 1.\n"
                + "For example, if the highest file found in a certain user's Gold Stars folder was game5.txt, you should save files to\n"
                + "\tthat user's Gold Stars folder as game6.txt.\n"
                + "Note: If no game files were found in a user's Gold Stars folder, you can save files to their Gold Stars folder\n"
                + "\tas game0.txt.";
        JFrame goldScriptStep1Frame = new JFrame("Using GoldScript: Step 1");
        goldScriptStep1Frame.setSize(750, 375);
        JTextArea goldScriptStep1TextArea = new JTextArea(output);
        goldScriptStep1TextArea.setEditable(false);
        goldScriptStep1TextArea.setFont(new java.awt.Font(null, Font.PLAIN, 14));
        goldScriptStep1Frame.add(goldScriptStep1TextArea);
        goldScriptStep1Frame.setVisible(true);
    }

    public static void goldScriptStep2() {
        output = "You can put game information for your game into the Gold Games app on Gold Stars by saving information to the\n"
                + "\ttext file for your game.\n"
                + "(The text file for your game was determined in Step 1.)\n"
                + "For example, you can save the user's high scores or other game statistics to the text file to make them appear\n"
                + "\tin the Games app in Gold Stars.";
        JFrame goldScriptStep2Frame = new JFrame("Using GoldScript: Step 2");
        goldScriptStep2Frame.setSize(750, 375);
        JTextArea goldScriptStep2TextArea = new JTextArea(output);
        goldScriptStep2TextArea.setEditable(false);
        goldScriptStep2TextArea.setFont(new java.awt.Font(null, Font.PLAIN, 14));
        goldScriptStep2Frame.add(goldScriptStep2TextArea);
        goldScriptStep2Frame.setVisible(true);
    }

    public static void step1() {
        output = "Search the user's Gold Stars folder (the folder that contains Gold Stars) for a file named app0.txt.\n"
                + "If a file named app0.txt is found, search for app1.txt. If app1.txt is found, search for app2.txt.\n"
                + "Continue searching until no more files are found.\n"
                + "Make note of the highest number file found.\n"
                + "Files from your app should be saved to the user's Gold Stars folder as .txt files with the word 'app' followed by\n"
                + "\tthe highest number file found plus 1.\n"
                + "For example, if the highest file found in a certain user's Gold Stars folder was app5.txt, you should save files to\n"
                + "\tthat user's Gold Stars folder as app6.txt.\n"
                + "Note: If no app files were found in a user's Gold Stars folder, you can save files to their Gold Stars folder\n"
                + "\tas app0.txt.";
        JFrame goldScriptStep1Frame = new JFrame("Using GoldScript: Step 1");
        goldScriptStep1Frame.setSize(750, 375);
        JTextArea goldScriptStep1TextArea = new JTextArea(output);
        goldScriptStep1TextArea.setEditable(false);
        goldScriptStep1TextArea.setFont(new java.awt.Font(null, Font.PLAIN, 14));
        goldScriptStep1Frame.add(goldScriptStep1TextArea);
        goldScriptStep1Frame.setVisible(true);
    }

    public static void step2() {
        output = "You can put app information for your app into the Gold Apps app on Gold Stars by saving information to the\n"
                + "\ttext file for your app.\n"
                + "(The text file for your app was determined in Step 1.)";
        JFrame goldScriptStep2Frame = new JFrame("Using GoldScript: Step 2");
        goldScriptStep2Frame.setSize(750, 375);
        JTextArea goldScriptStep2TextArea = new JTextArea(output);
        goldScriptStep2TextArea.setEditable(false);
        goldScriptStep2TextArea.setFont(new java.awt.Font(null, Font.PLAIN, 14));
        goldScriptStep2Frame.add(goldScriptStep2TextArea);
        goldScriptStep2Frame.setVisible(true);
    }

    public static void files() {
        JFrame filesFrame = new JFrame("Files");
        filesFrame.setSize(750, 375);
        filesFrame.getContentPane().setBackground(color1);
        filesFrame.setExtendedState(filesFrame.MAXIMIZED_BOTH);
        ArrayList<String> files = new ArrayList<String>();
        for (int i = 0; i < noteNames.size(); i++) {
            files.add(noteNames.get(i));
        }
        for (int i = 0; i < photoNames.size(); i++) {
            files.add(photoNames.get(i));
        }
        JLabel[] fileLabels = new JLabel[files.size()];
        for (int i = 0; i < noteNames.size(); i++) {
            fileLabels[i] = new JLabel(noteNames.get(i), SwingConstants.CENTER);
            fileLabels[i].addMouseListener(new FileListener(notes.get(i), false));
        }
        for (int i = noteNames.size(); i < noteNames.size() + photoNames.size(); i++) {
            fileLabels[i] = new JLabel("Photo " + (i + 1 - noteNames.size()), SwingConstants.CENTER);
            fileLabels[i].addMouseListener(new FileListener(files.get(i), true));
        }
        if (fileLabels.length == 0) {
            JLabel noFiles = new JLabel("You haven't saved any files to Gold Stars yet!", SwingConstants.CENTER);
            noFiles.setFont(new java.awt.Font(null, Font.BOLD, 16));
            noFiles.setForeground(color2);
            filesFrame.add(noFiles);
        } else {
            for (int i = 0; i < fileLabels.length; i++) {
                filesFrame.setLayout(new GridLayout(((noteNames.size() + photoNames.size() - 1) / 3) + 1, 3));
                fileLabels[i].setFont(new java.awt.Font(null, Font.BOLD, 16));
                fileLabels[i].setForeground(color2);
                filesFrame.add(fileLabels[i]);
            }
        }
        filesFrame.setVisible(true);
    }

    public static class FileListener implements MouseListener {
        String toDisplay;
        boolean picture;

        public FileListener(String name, boolean photo) {
            toDisplay = name;
            picture = photo;
        }

        public void mousePressed(MouseEvent e) {
            if (!picture) {
                if (passwordProtectedNotes) {
                    boolean access = password();
                    if (access == false) {
                        return;
                    }
                }
                JOptionPane.showMessageDialog(null, toDisplay, "Notes", JOptionPane.INFORMATION_MESSAGE);
            } else {
                if (passwordProtectedPhotos) {
                    boolean access = password();
                    if (access == false) {
                        return;
                    }
                }
                JFrame photoFrame = new JFrame("Photos");
                photoFrame.setVisible(true);
                photoFrame.setSize(1000, 600);
                ImageIcon image = new ImageIcon(toDisplay);
                JLabel photo = new JLabel(image);
                photoFrame.add(photo);
            }
        }

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    public static void help() {
        JFrame settingsFrame = new JFrame("Help");
        settingsFrame.setSize(750, 375);
        settingsFrame.setExtendedState(settingsFrame.MAXIMIZED_BOTH);
        settingsFrame.setLayout(new GridLayout(4, 3));
        settingsFrame.getContentPane().setBackground(color1);
        JLabel customize = new JLabel("Customize Gold Stars:", SwingConstants.CENTER);
        customize.setOpaque(true);
        customize.setBackground(color2);
        customize.setForeground(color1);
        customize.setFont(new java.awt.Font(null, Font.BOLD, 16));
        settingsFrame.add(customize);
        JLabel wallpaper = new JLabel("Set Wallpaper", SwingConstants.CENTER);
        wallpaper.setForeground(color2);
        wallpaper.setFont(new java.awt.Font(null, Font.BOLD, 16));
        wallpaper.addMouseListener(new WallpaperIntroListener());
        settingsFrame.add(wallpaper);
        JLabel theme = new JLabel("Change the Theme", SwingConstants.CENTER);
        theme.setForeground(color2);
        theme.setFont(new java.awt.Font(null, Font.BOLD, 16));
        theme.addMouseListener(new ThemeListener());
        settingsFrame.add(theme);
        JLabel generalSettings = new JLabel("Gold Stars Talk:", SwingConstants.CENTER);
        generalSettings.setOpaque(true);
        generalSettings.setBackground(color2);
        generalSettings.setForeground(color1);
        generalSettings.setFont(new java.awt.Font(null, Font.BOLD, 16));
        settingsFrame.add(generalSettings);
        JLabel changeLanguage = new JLabel("What is Gold Stars Talk?", SwingConstants.CENTER);
        changeLanguage.setForeground(color2);
        changeLanguage.setFont(new java.awt.Font(null, Font.BOLD, 16));
        changeLanguage.addMouseListener(new TalkIntroListener());
        settingsFrame.add(changeLanguage);
        JLabel changeTheme = new JLabel("What can I say to Gold Stars Talk?", SwingConstants.CENTER);
        changeTheme.setForeground(color2);
        changeTheme.setFont(new java.awt.Font(null, Font.BOLD, 16));
        changeTheme.addMouseListener(new TalkSuggestionsListener());
        settingsFrame.add(changeTheme);
        JLabel appSettings = new JLabel("System Tools:", SwingConstants.CENTER);
        appSettings.setOpaque(true);
        appSettings.setBackground(color2);
        appSettings.setForeground(color1);
        appSettings.setFont(new java.awt.Font(null, Font.BOLD, 16));
        settingsFrame.add(appSettings);
        JLabel calendarSettings = new JLabel("The Error Log", SwingConstants.CENTER);
        calendarSettings.setForeground(color2);
        calendarSettings.setFont(new java.awt.Font(null, Font.BOLD, 16));
        calendarSettings.addMouseListener(new ErrorsIntroListener());
        settingsFrame.add(calendarSettings);
        JLabel calculatorSettings = new JLabel("Commands", SwingConstants.CENTER);
        calculatorSettings.setForeground(color2);
        calculatorSettings.setFont(new java.awt.Font(null, Font.BOLD, 16));
        calculatorSettings.addMouseListener(new CommandsIntroListener());
        settingsFrame.add(calculatorSettings);
        JLabel moreInfo = new JLabel("More Information:", SwingConstants.CENTER);
        moreInfo.setOpaque(true);
        moreInfo.setBackground(color2);
        moreInfo.setForeground(color1);
        moreInfo.setFont(new java.awt.Font(null, Font.BOLD, 16));
        settingsFrame.add(moreInfo);
        JLabel aboutPrograms = new JLabel("About Programs", SwingConstants.CENTER);
        aboutPrograms.setForeground(color2);
        aboutPrograms.setFont(new java.awt.Font(null, Font.BOLD, 16));
        aboutPrograms.addMouseListener(new AboutProgramsIntroListener());
        settingsFrame.add(aboutPrograms);
        settingsFrame.setVisible(true);
    }

    public static class WallpaperIntroListener implements MouseListener {
        public void mousePressed(MouseEvent e) {
            output = "You can set a wallpaper to appear on the main screen of Gold Stars!\n"
                    + "To do so, first go to Settings, and then click Set Wallpaper.\n"
                    + "Next, place the wallpaper that you want to appear in the same folder that Gold Stars is in.\n"
                    + "After that, choose whether the wallpaper you want to set is in a .jpg or .png file.\n"
                    + "Then enter the name of the file (without the .jpg or .png extension).\n"
                    + "Finally, restart Gold Stars for the wallpaper to appear!";
            JOptionPane.showMessageDialog(null, output, "Set Wallpaper", JOptionPane.INFORMATION_MESSAGE);
        }

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    public static class ThemeListener implements MouseListener {
        public void mousePressed(MouseEvent e) {
            output = "In addition to setting a wallpaper, customize Gold Stars by changing the theme.\n"
                    + "Changing the theme will change the color(s) on the home screen of Gold Stars and in some apps.\n"
                    + "Many themes also feature a wallpaper on the home screen of Gold Stars.\n"
                    + "To change the theme, go to Settings > Change Theme.\n"
                    + "You can then click on a theme, or you can click Solid Color to just choose a background color instead.\n"
                    + "You will have to restart Gold Stars after choosing a theme or color for the change to take place.";
            JOptionPane.showMessageDialog(null, output, "Change the Theme", JOptionPane.INFORMATION_MESSAGE);
        }

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    public static class TalkIntroListener implements MouseListener {
        public void mousePressed(MouseEvent e) {
            output = "Gold Stars Talk is a personal assistant.\n"
                    + "You can have a conversation with Gold Stars Talk, or ask it to do stuff for you!\n"
                    + "For more information on what you can say to Gold Stars Talk, see the 'What can I say to Gold Stars Talk?' section of Help.";
            JOptionPane.showMessageDialog(null, output, "What is Gold Stars Talk?", JOptionPane.INFORMATION_MESSAGE);
        }

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    public static class TalkSuggestionsListener implements MouseListener {
        public void mousePressed(MouseEvent e) {
            output = "In addition to having a conversation with Gold Stars Talk, you can ask Gold Stars Talk to do things for you.\n"
                    + "For example, you can try asking Gold Stars Talk to 'add 2 numbers' or to 'open Gold Stars Stories.'";
            JOptionPane.showMessageDialog(null, output, "What can I say to Gold Stars Talk?", JOptionPane.INFORMATION_MESSAGE);
        }

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    public static class ErrorsIntroListener implements MouseListener {
        public void mousePressed(MouseEvent e) {
            output = "The Error Log is a list of some of the errors that Gold Stars may have encountered since the last time you opened it.\n"
                    + "To view errors in the Error Log, you can click on System Tools on the main menu, and then click Error Log.";
            JOptionPane.showMessageDialog(null, output, "The Error Log", JOptionPane.INFORMATION_MESSAGE);
        }

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    public static class AboutProgramsIntroListener implements MouseListener {
        public void mousePressed(MouseEvent e) {
            output = "For more information on specific programs, you can visit the About Programs section of Gold Stars.\n"
                    + "To get to About Programs, click on About on the main screen, and then click the About Programs button.\n"
                    + "From there, you can choose a program to view more information about.";
            JOptionPane.showMessageDialog(null, output, "About Programs", JOptionPane.INFORMATION_MESSAGE);
        }

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    public static class CommandsIntroListener implements MouseListener {
        public void mousePressed(MouseEvent e) {
            output = "Commands is like Gold Stars Talk without the conversation part.\n"
                    + "You can type a command of something you would like to happen to Gold Stars.\n"
                    + "For example, you can try typing 'multiply 2 numbers' or 'view notes' in Commands.";
            JOptionPane.showMessageDialog(null, output, "Commands", JOptionPane.INFORMATION_MESSAGE);
        }

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Error");
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        throw new UnsupportedOperationException("Error");
    }
}