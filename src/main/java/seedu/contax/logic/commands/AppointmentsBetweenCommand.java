package seedu.contax.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_DATE_END;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_DATE_START;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_TIME_END;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_TIME_START;

import java.time.LocalDateTime;

import seedu.contax.commons.core.GuiListContentType;
import seedu.contax.model.Model;
import seedu.contax.model.appointment.DateRangePredicate;

/**
 * Lists all Appointments within a period.
 */
public class AppointmentsBetweenCommand extends Command {

    public static final String COMMAND_WORD = "appointmentsBetween";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists appointments within a period. "
            + "Parameters: "
            + PREFIX_DATE_START + "STARTDATE "
            + PREFIX_TIME_START + "STARTTIME "
            + PREFIX_DATE_END + "ENDDATE "
            + PREFIX_TIME_END + "ENDTIME "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_DATE_START + "22-10-2022 "
            + PREFIX_TIME_START + "12:30 "
            + PREFIX_DATE_END + "22-10-2022 "
            + PREFIX_TIME_END + "16:30";

    public static final String MESSAGE_SUCCESS = "Listed appointments within the requested range.";
    public static final String MESSAGE_START_DATE_INVALID = "The start date provided is invalid!";
    public static final String MESSAGE_START_TIME_INVALID = "The start time provided is invalid!";
    public static final String MESSAGE_END_DATE_INVALID = "The end date provided is invalid!";
    public static final String MESSAGE_END_TIME_INVALID = "The end time provided is invalid!";

    private final LocalDateTime rangeStart;
    private final LocalDateTime rangeEnd;

    /**
     * Constructs an {@code AppointmentsBetweenCommand} object.
     *
     * @param rangeStart
     * @param rangeEnd
     */
    public AppointmentsBetweenCommand(LocalDateTime rangeStart, LocalDateTime rangeEnd) {
        this.rangeStart = rangeStart;
        this.rangeEnd = rangeEnd;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredAppointmentList(new DateRangePredicate(rangeStart, rangeEnd));
        return new CommandResult(MESSAGE_SUCCESS, GuiListContentType.APPOINTMENT);
    }
}
