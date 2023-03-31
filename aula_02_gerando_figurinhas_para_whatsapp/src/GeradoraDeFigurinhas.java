import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class GeradoraDeFigurinhas {

    public void cria(InputStream inputStream, String nomeArquivo) throws IOException{
        
        // leitura da imagem
        //InputStream inputStream = new URL("https://ampli-whitelabel-images.s3.amazonaws.com/ampli/logo-full.png").openStream();
        // InputStream é uma porta para receber bytes, recebendo dado de varios lugares, importante é ser em byte.
        BufferedImage imagemOriginal = ImageIO.read(inputStream);
        // BufferedImage é o que recebe o InputStream e assim ele transforma em imagem esse Input!

        // cria nova imagem em memória com transparência e com tamanho novo
        int largura = imagemOriginal.getWidth();
        int altura = imagemOriginal.getHeight();
        int novaAltura = altura + 100;
        BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);

        // copiar a imagem original pra novo imagem (em memória)
        Graphics2D graphics2D = (Graphics2D) novaImagem.getGraphics();
        graphics2D.drawImage(imagemOriginal, 0, 0, null);

        // configurar fonte imagem
        var fonteImagem = new Font(Font.SANS_SERIF, Font.BOLD, 64);
        graphics2D.setColor(Color.GREEN);
        graphics2D.setFont(fonteImagem);

        // escrever um mensagem na nova imagem
        graphics2D.drawString("Topezera", 300, novaAltura-50);

        // escrever a nova imagem em um arquivo
        ImageIO.write(novaImagem, "png", new File(nomeArquivo));
    }
    
}
