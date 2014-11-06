
package negocio;

import apresentacao.FormCadastroProfessor;
import banco.BdProfessor;
import objeto.Professor;

public class NegocioProfessor {
    BdProfessor objBdProfessor = new BdProfessor();
    public boolean VerificadorProfessor(FormCadastroProfessor objForm ,Professor objProfessor){
        
        String MensagemErro;
        boolean retorno=true;
        if(objProfessor.getNome().isEmpty()) {
            retorno = false;
            objForm.jLabelNomeErro.setVisible(true);
        }
        if(objProfessor.getEmail().isEmpty()){
            retorno =  false;
            objForm.jLabelEmailErro.setVisible(true);
        }
       
        if(objProfessor.getCpf().length()<2 || !VerificadorCPF(objProfessor.getCpf()))
        {
            retorno = false;
            objForm.jLabelCpfErro.setVisible(true);
        }
        if(retorno)
            retorno = objBdProfessor.InserirProfessor(objProfessor);
        return retorno;
    }
    static public boolean VerificadorCPF (String strCpf )
   {
      int     d1, d2;
      int     digito1, digito2, resto;
      int     digitoCPF;
      String  nDigResult;

      d1 = d2 = 0;
      digito1 = digito2 = resto = 0;

      for (int nCount = 1; nCount < strCpf.length() -1; nCount++)
      {
         digitoCPF = Integer.valueOf (strCpf.substring(nCount -1, nCount)).intValue();

         //multiplique a ultima casa por 2 a seguinte por 3 a seguinte por 4 e assim por diante.
         d1 = d1 + ( 11 - nCount ) * digitoCPF;

         //para o segundo digito repita o procedimento incluindo o primeiro digito calculado no passo anterior.
         d2 = d2 + ( 12 - nCount ) * digitoCPF;
      };

      //Primeiro resto da divisão por 11.
      resto = (d1 % 11);

      //Se o resultado for 0 ou 1 o digito é 0 caso contrário o digito é 11 menos o resultado anterior.
      if (resto < 2)
         digito1 = 0;
      else
         digito1 = 11 - resto;

      d2 += 2 * digito1;

      //Segundo resto da divisão por 11.
      resto = (d2 % 11);

      //Se o resultado for 0 ou 1 o digito é 0 caso contrário o digito é 11 menos o resultado anterior.
      if (resto < 2)
         digito2 = 0;
      else
         digito2 = 11 - resto;

      //Digito verificador do CPF que está sendo validado.
      String nDigVerific = strCpf.substring (strCpf.length()-2, strCpf.length());

      //Concatenando o primeiro resto com o segundo.
      nDigResult = String.valueOf(digito1) + String.valueOf(digito2);

      //comparar o digito verificador do cpf com o primeiro resto + o segundo resto.
      return nDigVerific.equals(nDigResult);
   }
    public boolean excluir(FormCadastroProfessor form_professor)
    {
        BdProfessor obj_Bdprofessor = new BdProfessor();
        boolean retorno=false;
        if(form_professor.jTextCpf.getText().isEmpty() || !form_professor.jTextCpf.isEditable())
        {
            retorno = obj_Bdprofessor.Excluir(form_professor.jTextCpf.getText());            
        }
        return retorno;
    }
}
