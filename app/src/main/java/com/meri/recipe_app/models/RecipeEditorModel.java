package com.meri.recipe_app.models;

public class RecipeEditorModel {

    public boolean validateRecipeName(String inputRecipeName){

        if (inputRecipeName.equals(""))
            return false;
        if (inputRecipeName.length() > 100)
            return false;

        return true;
    }
}
