package com.anjali.internship_challenge;

import com.anjali.internship_challenge.data.ToDos;

public interface ToDosListener {
    void onNoteClicked(ToDos toDos, int position);
}
