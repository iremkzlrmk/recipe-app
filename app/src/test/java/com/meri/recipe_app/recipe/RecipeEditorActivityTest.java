package com.meri.recipe_app.recipe;

import static org.junit.Assert.*;

import android.content.Intent;
import android.widget.EditText;
import android.widget.ImageView;

import com.meri.recipe_app.models.RecipeEditorModel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RecipeEditorActivityTest {

    @Test
    public void testSetRecipeInputs(){
        RecipeEditorModel recipeEditor = new RecipeEditorModel();

        boolean validation = recipeEditor.validateRecipeName("sous vide steak");
        assertEquals(validation, true);
    }


}