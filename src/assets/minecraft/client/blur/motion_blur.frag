#version 120

uniform sampler2D texture;
uniform vec2 resolution;
uniform float blurIntensity;

void main() {
    // 获取当前片元的坐标
    vec2 texCoord = gl_TexCoord[0].xy;
    
    // 根据模糊强度计算偏移量
    float offset = blurIntensity / resolution.x;
    
    vec4 color = vec4(0.0);
    
    // 对当前像素周围的像素进行采样并累加模糊效果
    color += texture2D(texture, texCoord + vec2(-offset, -offset));
    color += texture2D(texture, texCoord + vec2( 0.0, -offset));
    color += texture2D(texture, texCoord + vec2( offset, -offset));
    color += texture2D(texture, texCoord + vec2(-offset,  0.0));
    color += texture2D(texture, texCoord);
    color += texture2D(texture, texCoord + vec2( offset,  0.0));
    color += texture2D(texture, texCoord + vec2(-offset,  offset));
    color += texture2D(texture, texCoord + vec2( 0.0,  offset));
    color += texture2D(texture, texCoord + vec2( offset,  offset));

    // 计算平均颜色并返回
    gl_FragColor = color / 9.0;
}
