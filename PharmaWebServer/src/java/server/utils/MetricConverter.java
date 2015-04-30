/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.utils;

/**
 *
 * @author Admin
 */
public class MetricConverter {
    
    public static enum UNIT{
        mcL, //microlitre
        mL, //millilitre
        L,  //litre
        
        mcg, //microgram
        mg, //milligram
        g,  //gram
        kg  //kilogram
    }
    
    
    
    //weight conversion
    public float convertMicrogramToMilligram(float weight){
        return (weight / (float)1e3);
    }
    public float convertMicrogramToGram(float weight){
        return (weight / (float)1e6);
    }
    public float convertMicrogramToKilogram(float weight){
        return (weight / (float)1e9);
    }
    public float convertMilligramToGram(float weight){
        return (weight / (float)1e3);
    }
    public float convertMilligramToKilogram(float weight){
        return (weight / (float)1e6);
    }
    public float convertGramToKilogram(float weight){
        return (weight / (float)1e3);
    }
    public float convertKilogramToGram(float weight){
        return (weight * (float)1e3);
    }
    public float convertKilogramToMilligram(float weight){
        return (weight * (float)1e6);
    }
    public float convertKilogramToMicrogram(float weight){
        return (weight * (float)1e9);
    }
    public float convertGramToMilligram(float weight){
        return (weight * (float)1e3);
    }
    public float convertGramToMicrogram(float weight){
        return (weight * (float)1e6);
    }
    public float convertMilligramToMicrogram(float weight){
        return (weight * (float)1e3);
    }
    
    //volume conversion
    public float convertMicrolitreToMillilitre(float volume){
        return (volume / (float)1e3);
    }
    public float convertMicrolitreToLitre(float volume){
        return (volume / (float)1e6);
    }
    public float convertMillilitreToLitre(float volume){
        return (volume / (float)1e3);
    }
    public float convertLitreToMillilitre(float volume){
        return (volume * (float)1e3);
    }
    public float convertLitreToMicrolitre(float volume){
        return (volume * (float)1e6);
    }
    public float convertMillilitreToMicrolitre(float volume){
        return (volume * (float)1e3);
    }
    
}
