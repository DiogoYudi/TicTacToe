import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;

public class Main extends JFrame {
    JButton[] jb = new JButton[9];
    boolean xo = false;
    boolean[] click = new boolean[9];
    public Main(){
        setVisible(true); //Abrir a telinha
        setTitle("Jogo da Velha"); //Titulo
        setDefaultCloseOperation(3); //Para fechar o jogo quando apertar o "x"
        setLayout(null); //Definir o layout posteriormente
        setBounds(100, 100, 700, 500); //Definir o tamanho da tela e em que local da tela aparecer
        int cont = 0;
        for(int i = 0; i < 3; i++){ //Criar a linha do Jogo de Velha
            for(int j = 0; j < 3; j++){ //Criar a coluna do Jogo da Velha
                jb[cont] = new JButton(); //Iniciar a matriz
                add(jb[cont]); //Adicionar as linhas/coluna
                jb[cont].setBounds((100*i) + 50, (100*j) + 50, 95, 95); //Definir o tamanho dos blocos e localização delas
                jb[cont].setFont(new Font("Arial", Font.BOLD,40));
                cont++;
            }
        }

        for(int i = 0; i < 9; i++){ //Definir um valor falso para cada blocos, para impossibilitar de realizar uma jogada no mesmo bloco ja selecionado anteriomente
            click[i] = false;
        }

        for (int i = 0; i < 9; i++) {
            final int index = i; //Necessario essa parte para que não dê o erro "local variables referenced from an inner class must be final or effectively final"
            jb[index].addActionListener(new java.awt.event.ActionListener() { //Adicionar no bloco um dos simbolos
                public void actionPerformed(ActionEvent ae) {
                    if (click[index] == false) {
                        click[index] = true; //Definir o bloco como true para que não possa mais jogar nesse mesmo bloco
                        change(jb[index]); //Chamar a função de trocar os simbolos
                    }
                }
            });
        }
    }

    public void change(JButton btn){ //Clase para mudar o simbolo do jogo (Quando o jogador "O" jogar, mudará para o "X"
        if(xo){
            btn.setText("X");
            xo = false;
        } else{
            btn.setText("O");
            xo = true;
        }
        winner();
    }

    public void winner(){ //Verificar o vencedor
        int cont = 0;
        for(int i = 0; i < 9; i++){
            if(click[i] == true){
                cont++;
            }
        }
        for(int i = 0; i < 9; i += 3){ //Verificando se algum jogador ganhou verticalmente
            if (jb[i].getText() == "X" && jb[i + 1].getText() == "X" && jb[i + 2].getText() == "X") {
                JOptionPane.showMessageDialog(null, "X ganhou");
                limpar();
            }
            else if (jb[i].getText() == "O" && jb[i + 1].getText() == "O" && jb[i + 2].getText() == "O") {
                JOptionPane.showMessageDialog(null, "O ganhou");
                limpar();
            }
        }
        for(int i = 0; i < 3; i++){ //Verificando se algum jogador ganhou horizontalmente
            if (jb[i].getText() == "X" && jb[i + 3].getText() == "X" && jb[i + 6].getText() == "X") {
                JOptionPane.showMessageDialog(null, "X ganhou");
                limpar();
            }
            else if (jb[i].getText() == "O" && jb[i + 3].getText() == "O" && jb[i + 6].getText() == "O") {
                JOptionPane.showMessageDialog(null, "O ganhou");
                limpar();
            }
        }
        // Verifica diagonais
        if (jb[0].getText() == "X" && jb[4].getText() == "X" && jb[8].getText() == "X") {
            JOptionPane.showMessageDialog(null, "X ganhou");
            limpar();
        }
        else if (jb[0].getText() == "O" && jb[4].getText() == "O" && jb[8].getText() == "O") {
            JOptionPane.showMessageDialog(null, "O ganhou");
            limpar();
        }

        else if (jb[2].getText() == "X" && jb[4].getText() == "X" && jb[6].getText() == "X") {
            JOptionPane.showMessageDialog(null, "X ganhou");
            limpar();
        }
        else if (jb[2].getText() == "O" && jb[4].getText() == "O" && jb[6].getText() == "O") {
            JOptionPane.showMessageDialog(null, "O ganhou");
            limpar();
        }
        else{
            draw(cont); //Chama a função de verificar se é empate
        }
    }

    public void draw(int cont){ //Verifica se o jogo empatou
        if(cont == 9){
            JOptionPane.showMessageDialog(null, "Empate");
            limpar();
        }
    }

    public void limpar(){ //Limpa todos os blocos
        for(int i = 0; i < 9; i++){
            jb[i].setText("");
            click[i] = false;
        }
    }

    public static void main(String[] args) {
        new Main();
    }
}