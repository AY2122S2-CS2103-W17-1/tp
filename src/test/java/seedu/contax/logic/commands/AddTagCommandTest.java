package seedu.contax.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.contax.commons.core.GuiSettings;
import seedu.contax.logic.commands.exceptions.CommandException;
import seedu.contax.model.Model;
import seedu.contax.model.ReadOnlyAddressBook;
import seedu.contax.model.ReadOnlySchedule;
import seedu.contax.model.ReadOnlyUserPrefs;
import seedu.contax.model.appointment.Appointment;
import seedu.contax.model.person.Person;
import seedu.contax.model.tag.Tag;
import seedu.contax.testutil.TagBuilder;

class AddTagCommandTest {
    @Test
    public void constructor_nullTag_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddTagCommand(null));
    }

    @Test
    public void execute_tagAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingTagAdded modelStub = new ModelStubAcceptingTagAdded();
        Tag validTag = new TagBuilder().build();

        CommandResult commandResult = new AddTagCommand(validTag).execute(modelStub);
        assertEquals(String.format(AddTagCommand.MESSAGE_SUCCESS, validTag), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validTag), modelStub.tagsAdded);
    }

    @Test
    public void execute_duplicateTag_throwsCommandException() {
        ModelStubWithTag modelStub = new ModelStubWithTag(new TagBuilder().build());
        Tag duplicate = new TagBuilder().build();

        assertThrows(CommandException.class, () -> new AddTagCommand(duplicate).execute(modelStub));
    }

    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook addressBook) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePerson(Person target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPerson(Person target, Person editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getScheduleFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setScheduleFilePath(Path scheduleFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setSchedule(ReadOnlySchedule schedule) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlySchedule getSchedule() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasAppointment(Appointment appointment) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasOverlappingAppointment(Appointment appointment) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteAppointment(Appointment appointment) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addAppointment(Appointment appointment) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAppointment(Appointment target, Appointment editedAppointment) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasTag(Tag tag) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addTag(Tag tag) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteTag(Tag tagToDelete) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void removeTagFromPersons(Tag tagToDelete) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Tag> getTagList() {
            throw new AssertionError("This method should not be called.");
        }
    }

    private class ModelStubWithTag extends ModelStub {
        private final Tag tag;

        ModelStubWithTag(Tag tag) {
            requireNonNull(tag);
            this.tag = tag;
        }

        @Override
        public boolean hasTag(Tag tag) {
            requireNonNull(tag);
            return this.tag.isSameTag(tag);
        }
    }

    private class ModelStubAcceptingTagAdded extends ModelStub {
        final ArrayList<Tag> tagsAdded = new ArrayList<>();

        @Override
        public boolean hasTag(Tag tag) {
            requireNonNull(tag);
            return tagsAdded.stream().anyMatch(tag::isSameTag);
        }

        @Override
        public void addTag(Tag tag) {
            requireNonNull(tag);
            tagsAdded.add(tag);
        }
    }
}
