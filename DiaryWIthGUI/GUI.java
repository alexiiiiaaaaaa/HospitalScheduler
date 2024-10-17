import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.*;

public class GUI {

    private JFrame frame;
    private JTable calendarTable;
    private JLabel monthLabel;
    private DefaultTableModel tableModel;
    private int currentYear, currentMonth; //generating the int variables for current date
    private DiaryItemsSet diaryItemsSet; // generating the diary items variable 

    /**
     * GUI constructor
     * @param diaryItemsSet
     */
    public GUI(DiaryItemsSet diaryItemsSet) {
        this.diaryItemsSet = diaryItemsSet;

        frame = new JFrame("Календарь");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLayout(new BorderLayout());

        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BorderLayout());

        JButton prevButton = new JButton("<"); //actions to change the month by 1 less
        prevButton.addActionListener(e -> {
            currentMonth--;
            if (currentMonth < 0) {
                currentMonth = 11;
                currentYear--;
            }
            updateCalendar();
        });

        JButton nextButton = new JButton(">"); //actions to change the month by 1 more
        nextButton.addActionListener(e -> {
            currentMonth++;
            if (currentMonth > 11) {
                currentMonth = 0;
                currentYear++;
            }
            updateCalendar();
        });

        monthLabel = new JLabel("", SwingConstants.CENTER); //giving the borders to the month label
        headerPanel.add(prevButton, BorderLayout.WEST);
        headerPanel.add(monthLabel, BorderLayout.CENTER);
        headerPanel.add(nextButton, BorderLayout.EAST);

        tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        calendarTable = new JTable(tableModel);
        calendarTable.setRowSelectionAllowed(false);
        calendarTable.setDefaultRenderer(Object.class, new CalendarCellRenderer());
        calendarTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) { //method to show the events on the date if mouse clicked
                int row = calendarTable.rowAtPoint(e.getPoint());
                int column = calendarTable.columnAtPoint(e.getPoint());

                if (row >= 0 && column >= 0) {
                    Object value = calendarTable.getValueAt(row, column);

                    if (value != null && !value.toString().isEmpty()) {
                        int day = Integer.parseInt(value.toString());
                        LocalDate date = LocalDate.of(currentYear, currentMonth + 1, day);
                        showEventsPopup(date, e.getX(), e.getY());
                    }
                }
            }
        });

        frame.add(headerPanel, BorderLayout.NORTH);
        frame.add(new JScrollPane(calendarTable), BorderLayout.CENTER);

        LocalDate today = LocalDate.now();
        currentYear = today.getYear();
        currentMonth = today.getMonthValue() - 1;

        updateCalendar();
        frame.setVisible(true);
    }

    /**
     * method to update calendar data
     */
    private void updateCalendar() {
        tableModel.setRowCount(0);
        tableModel.setColumnCount(7);

        String[] daysOfWeek = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
        tableModel.setColumnIdentifiers(daysOfWeek);

        Calendar calendar = Calendar.getInstance();
        calendar.set(currentYear, currentMonth, 1);
        int startDay = calendar.get(Calendar.DAY_OF_WEEK) - 2;
        if (startDay < 0) startDay = 6;
        int numberOfDays = YearMonth.of(currentYear, currentMonth + 1).lengthOfMonth();

        Object[] week = new Object[7];
        for (int i = 0; i < startDay; i++) {
            week[i] = "";
        }

        int day = 1;
        for (int i = startDay; i < 7; i++) {
            week[i] = day++;
        }
        tableModel.addRow(week);

        while (day <= numberOfDays) {
            week = new Object[7];
            for (int i = 0; i < 7; i++) {
                if (day <= numberOfDays) {
                    week[i] = day++;
                } else {
                    week[i] = "";
                }
            }
            tableModel.addRow(week);
        }

        monthLabel.setText(String.format("%1$tB %1$tY", calendar.getTime()));
    }

    /**
     * method to display Events in the calendar GUI
     * @param date
     * @param x
     * @param y
     */
    private void showEventsPopup(LocalDate date, int x, int y) {
        JPopupMenu popup = new JPopupMenu();
        Set<DiaryItem> dayEvents = diaryItemsSet.searchDiaryItemsByDate(date);
        if (dayEvents.isEmpty()) {
            popup.add(new JMenuItem("No events"));
        } else {
            for (DiaryItem event : dayEvents) {
                popup.add(new JMenuItem(event.getFromDateTime().toLocalTime()+"-" + event.getToDateTime().toLocalTime() + " | " + event.getTreatmentType()));
            }
        }
        popup.show(calendarTable, x, y);
    }

    /**
     * method to render the cells (it just paints the cells)
     */
    private class CalendarCellRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            if (value != null && !value.toString().isEmpty()) {
                int day = Integer.parseInt(value.toString());
                LocalDate date = LocalDate.of(currentYear, currentMonth + 1, day);
                if (date.equals(LocalDate.now())) { //painting the today's day cell
                    cell.setForeground(Color.WHITE);
                    cell.setBackground(Color.BLACK);
                } else if (diaryItemsSet.searchDiaryItemsByDate(date).size() > 0) { //painting the dates with diary items
                    cell.setBackground(Color.red);
                } else {  //painting all other cells
                    cell.setBackground(Color.DARK_GRAY);
                }
            } else { //painting the cells without dates
                cell.setBackground(Color.GRAY);
            }

            return cell;
        }
    }
}