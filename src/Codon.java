/**
 * Enumeration used to translate 3-base sequences to Amino Acids
 * @author Instructors
 */
public enum Codon {

    UUU("Phe", 'F'), UUC("Phe", 'F'),
    UUA("Leu", 'L'), UUG("Leu", 'L'),
    UCU("Ser", 'S'), UCC("Ser", 'S'),
    UCA("Ser", 'S'), UCG("Ser", 'S'),
    UAU("Tyr", 'Y'), UAC("Tyr", 'Y'),
    UAA("Stop", '*'), UAG("Stop",'*'),
    UGU("Cys", 'C'), UGC("Cys", 'C'),
    UGA("Stop", '*'), UGG("Trp", 'W'),
    CUU("Leu", 'L'), CUC("Leu", 'L'),
    CUA("Leu", 'L'), CUG("Leu", 'L'),
    CCU("Pro", 'P'), CCC("Pro", 'P'),
    CCA("Pro", 'P'), CCG("Pro", 'P'),
    CAU("His", 'H'), CAC("His", 'H'),
    CAA("Gln", 'Q'), CAG("Gln", 'Q'),
    CGU("Arg", 'R'), CGC("Arg", 'R'),
    CGA("Arg", 'R'), CGG("Arg", 'R'),
    AUU("Ile", 'I'), AUC("Ile", 'I'),
    AUA("Ile", 'I'), AUG("Met", 'M'),
    ACU("Thr", 'T'), ACC("Thr", 'T'),
    ACA("Thr", 'T'), ACG("Thr", 'T'),
    AAU("Asn", 'N'), AAC("Asn", 'N'),
    AAA("Lys", 'K'), AAG("Lys", 'K'),
    AGU("Ser", 'S'), AGC("Ser", 'S'),
    AGA("Arg", 'R'), AGG("Arg", 'R'),
    GUU("Val", 'V'), GUC("Val", 'V'),
    GUA("Val", 'V'), GUG("Val", 'V'),
    GCU("Ala", 'A'), GCC("Ala", 'A'),
    GCA("Ala", 'A'), GCG("Ala", 'A'),
    GAU("Asp", 'D'), GAC("Asp", 'D'),
    GAA("Glu", 'E'), GAG("Glu", 'E'),
    GGU("Gly", 'G'), GGC("Gly", 'G'),
    GGA("Gly", 'G'), GGG("Gly", 'G');

    /**
     * Three-letter representation of an amino acid
     */
    private String threeLetterCode;
    /**
     * One-letter representation of an amino acid
     */
    private char oneLetterCode;

    /**
     * Constructor that sets the three-letter and one-letter representations of a amino acid translated from a codon
     * @param threeLetterCode
     * @param oneLetterCode
     */
    private Codon(String threeLetterCode, char oneLetterCode){
        this.threeLetterCode = threeLetterCode;
        this.oneLetterCode = oneLetterCode;
    }

    /**
     * Gets three letter code of an amino acid
     * @return three letter code of an amino acid
     */
    public String getThreeLetterCode(){
        return threeLetterCode;
    }

    /**
     * Gets one letter code of an amino acid
     * @return one letter code of an amino acid
     */
    public char getOneLetterCode(){
        return oneLetterCode;
    }

    /**
     * Returns amino acid value of a codon
     * @param codon 3-base sequence
     * @return amino acid represented by the codon
     */
    public static Codon getCodon(String codon){
        codon = codon.toUpperCase();
        codon = codon.replace('T', 'U');
        return valueOf(codon);
    }
}