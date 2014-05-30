package AYLLU.model.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ANDREA
 */
public class InformationRequestForm implements Serializable{

    private String id;
    private String formTittle;
    private List<FormQuestion> form;

    public InformationRequestForm() {
        this.id = "";
        this.formTittle = "";
        this.form = new ArrayList<FormQuestion>();
    }

    public InformationRequestForm(String formTittle) {
        this.id = "";
        this.formTittle = formTittle;
        this.form = new ArrayList<FormQuestion>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFormTittle() {
        return formTittle;
    }

    public void setFormTittle(String formTittle) {
        this.formTittle = formTittle;
    }

    public List<FormQuestion> getForm() {
        return form;
    }

    public void setForm(List<FormQuestion> form) {
        this.form = form;
    }

    public void addFormQuestion(FormQuestion question) {
        form.add(question);
    }

    public FormQuestion getFormQuestion(int index) {
        return form.get(index);
    }

    public void addOpenAnswerQuestion(String question) {
        FormQuestion formQuestion = new FormQuestion(question);
        form.add(formQuestion);
    }

    public void addMultipleChoiceQuestion(String question, List<String> possibleAnswers) {
        FormQuestion formQuestion = new FormQuestion(question, possibleAnswers, true);
        form.add(formQuestion);
    }

    public void addSelectOneQuestion(String question, List<String> possibleAnswers) {
        FormQuestion formQuestion = new FormQuestion(question, possibleAnswers, false);
        form.add(formQuestion);
    }

    public List<String> getSelectedAnswersFromQuestion(int index) {
        return form.get(index).getSelectedAnswers();
    }

    public String getAnswerFromQuestion(int index) {
        return form.get(index).getAnswer();
    }

    public int getSize() {
        return form.size();
    }

    public void changeSelected(String idAnswer) {//formPosition_answerPosition
        String split[] = idAnswer.split("_");
        int formIndex = (int) Integer.valueOf(split[0]);
        int answerIndex = (int) Integer.valueOf(split[1]);

        form.get(formIndex).changeSelected(answerIndex);
    }

    public boolean isPossibleAnswerSelected(String idAnswer){
        String split[] = idAnswer.split("_");
        int formIndex = (int) Integer.valueOf(split[0]);
        int answerIndex = (int) Integer.valueOf(split[1]);

        return form.get(formIndex).isSelected(answerIndex);
    }

    public void setOpenAnswer(String strformIndex, String openAnswer) {
        int formIndex = (int) Integer.valueOf(strformIndex);
        form.get(formIndex).setOpenAnswer(openAnswer);
    }

    public boolean isFormComplete() {
        for (int i = 0; i < form.size(); i++) {
            if (form.get(i).isOpenAnswer) {
                if (form.get(i).getAnswer().isEmpty()) {
                    return false;
                }
            } else if (form.get(i).getSelectedAnswers().isEmpty()) {
                return false;
            }
        }

        return true;
    }

    public void clearAllSelected() {
        for (int i = 0; i < form.size(); i++) {
            form.get(i).clearSelectedAnswers();
        }
    }

    public void clearSelected(int formIndex) {
        form.get(formIndex).clearSelectedAnswers();
    }

    public class FormQuestion implements Serializable{

        private String question;
        private String openAnswer;
        private boolean isMultipleChoice;
        private boolean isOpenAnswer;
        private List<String> possibleAnswers;
        private List<Integer> selectedAnswerIndexes;

        public FormQuestion(String question) {
            this.question = question;
            this.openAnswer = "";
            this.isMultipleChoice = false;
            this.isOpenAnswer = true;
            this.possibleAnswers = new ArrayList<String>();
            this.selectedAnswerIndexes = new ArrayList<Integer>();
        }

        public FormQuestion(String question, List<String> possibleAnswers, boolean isMultipleChoice) {
            this.question = question;
            this.openAnswer = "";
            this.isMultipleChoice = isMultipleChoice;
            this.isOpenAnswer = false;
            this.possibleAnswers = possibleAnswers;
            this.selectedAnswerIndexes = new ArrayList<Integer>();
        }

        public boolean isMultipleChoice() {
            return isMultipleChoice;
        }

        public boolean isOpenAnswer() {
            return isOpenAnswer;
        }

        public String getQuestion() {
            return question;
        }

        public List<String> getPossibleAnswers() {
            return possibleAnswers;
        }

        public void clearSelectedAnswers(){
            openAnswer="";
            selectedAnswerIndexes.clear();
        }

        public void changeSelected(int i) {
            if (!isMultipleChoice) {
                selectedAnswerIndexes.clear();
            }
            if (selectedAnswerIndexes.contains(i)) {
                if (selectedAnswerIndexes.size() == 1) {
                    selectedAnswerIndexes.clear();
                } else {
                    for (int j = 0; j < selectedAnswerIndexes.size(); j++) {
                        if (selectedAnswerIndexes.get(j) == i) {
                            selectedAnswerIndexes.remove(j);
                        }
                    }
                }
                return;
            }
            selectedAnswerIndexes.add(i);
        }

        public String getOpenAnswer() {
            return openAnswer;
        }

        public void setOpenAnswer(String openAnswer) {
            this.openAnswer = openAnswer;
        }

        public List<String> getSelectedAnswers() {
            if (isOpenAnswer) {
                List<String> openAnswerLst = new ArrayList<String>();
                openAnswerLst.add(openAnswer);
                return openAnswerLst;
            }

            List<String> selectedAnswers = new ArrayList<String>();
            for (int i = 0; i < selectedAnswerIndexes.size(); i++) {
                selectedAnswers.add(possibleAnswers.get(selectedAnswerIndexes.get(i)));
            }
            return selectedAnswers;
        }

        public String getAnswer() {
            if (isOpenAnswer) {
                return openAnswer;
            }
            if (!isMultipleChoice) {
                if (selectedAnswerIndexes.isEmpty()) {
                    return "";
                }
                return possibleAnswers.get(this.selectedAnswerIndexes.get(0));
            }

            String answer = "";
            for (int i = 0; i < selectedAnswerIndexes.size(); i++) {
                answer += possibleAnswers.get(selectedAnswerIndexes.get(i));
                if (i != selectedAnswerIndexes.size() - 1) {
                    answer += ";";
                }
            }
            return answer;
        }

        public boolean isSelected(int i){
            return selectedAnswerIndexes.contains(i);
        }
    }
}
