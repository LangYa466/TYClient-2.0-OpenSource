#version 120

attribute vec4 vertexPosition;
attribute vec2 textureCoord;
varying vec2 fragTextureCoord;

void main() {
    gl_Position = vertexPosition;
    fragTextureCoord = textureCoord;
}