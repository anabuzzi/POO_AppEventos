package br.senai.sc.eventos;

public enum CodesEnum {
    REQUEST_CODE_NEW_EVENT(1),
    REQUEST_CODE_EVENT_EDITION(2),
    REQUEST_CODE_DELETE_EVENT(3),
    RESULT_CODE_NEW_EVENT(10),
    RESULT_CODE_NEW_EDITED_EVENT(11),
    RESULT_CODE_DELETE_EVENT(12);

    int value;

    CodesEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static boolean isCodesNewEvent(int requestCode, int resultCode) {
        return REQUEST_CODE_NEW_EVENT.value == requestCode && RESULT_CODE_NEW_EVENT.value == resultCode;
    }

    public static boolean isCodesNewEditedEvent(int requestCode, int resultCode) {
        return REQUEST_CODE_EVENT_EDITION.value == requestCode && RESULT_CODE_NEW_EDITED_EVENT.value == resultCode;
    }

    public static boolean isCodesDeletedEvent(int requestCode, int resultCode) {
        return REQUEST_CODE_DELETE_EVENT.value == requestCode || RESULT_CODE_DELETE_EVENT.value == resultCode;
    }
}
